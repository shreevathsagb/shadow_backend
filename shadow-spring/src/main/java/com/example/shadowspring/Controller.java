 package com.example.shadowspring;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class Controller {
	@Autowired
	private Repo repo;

	@GetMapping("/findusers")
	public List<Entity> findusers() {
		return this.repo.findAll();
	}
	
	@GetMapping("/findprofile/{username}")
	public String findprofile(@PathVariable String username) {
		return repo.getUserProfileUsername(username);
	}
	
	@GetMapping("/finduser/{Username}")
	public Entity find(@PathVariable String Username) {
		return repo.getUserByUsername(Username);
	}

	@DeleteMapping("/delete/{username}")
	public String deletemap(@PathVariable String username) {
		repo.deleteById(username);
		return "deleted";
	}

	@PostMapping("/registeruser")
	public String saveuser(@RequestBody Entity user) {
		repo.save(user);
		return "user saved";
	}

	@PutMapping("/updateuser")
	public String updateuser(@RequestBody Entity user) {
		repo.save(user);
		return ("saved");
	}

	@PutMapping("/blocked/{status}/{username}")
	public ResponseEntity<String> setBlocked(@PathVariable String status, @PathVariable String username) {
		int updatedRows = repo.setBlocked(status, username);
		if (updatedRows > 0) {
			return ResponseEntity.ok("Blocked");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
	}

	@Autowired
	private NotifyRepo Nrepo;

	@GetMapping("/getnotifications")
	public List<Notification> getnotify() {
		return this.Nrepo.findAll();
	}
	
	@GetMapping("/getnotify")
	public List<Notification> getnoyifications(){
		return this.Nrepo.getNotify();
	}
	
	@Autowired
	private Postrepo postrepo;

	@PostMapping("/addpost")
	public String addpost(@RequestBody Post obj) {
		this.postrepo.save(obj);
		return "posted";
	}
	
	@GetMapping("/getlikes/{id}")
	public int getlikes(@PathVariable int id) {
	    Optional<Post> postOptional = postrepo.findById(id);
	    if (postOptional.isPresent()) {
	        Post post = postOptional.get();
	        return post.getLikes();
	    } else {
	      return 0;
	    }
	}

	@DeleteMapping("/deleltepost/{id}")
	public String deletepost(@PathVariable int id) {
		this.postrepo.deleteById(id);
		return "deleted";
	}

	@GetMapping("/getpost")
	public List<Post> findpost() {
		return this.postrepo.findAllOrderByPriceDesc();
	}
	
	@GetMapping("/getadminpost")
	public List<Post> findadminpost() {
		return this.postrepo.findAllbyreport();
	}
	
	@PutMapping("/addlike/{id}")
	public String addlike(@PathVariable int id) {
		postrepo.setAddLike(id);
		return "liked";
	}

	@PutMapping("/minuslike/{id}")
	public String minuslike(@PathVariable int id) {
		postrepo.setMinusLike(id);
		return "unliked";
	}
	
	@PutMapping("/plusreport/{id}")
	public String plusreport(@PathVariable int id) {
		postrepo.setPlusreport(id);
		return "reported";
	}
	
	@GetMapping("/profileposts/{user}")
	public List<Post> getprofilepost(@PathVariable String user) {
		return this.postrepo.findPostu(user);
	}

	@GetMapping("/search/{genre}")
	public List<Post> search(@PathVariable String genre) {
		return this.postrepo.Search(genre);
	}

	@GetMapping("/getbyid/${username}")
	public Optional<Entity> getbyid(@PathVariable String username) {
		return this.repo.findById(username);
	}

	@GetMapping("/getbyemail/{email}")
	public Optional<Entity> getemail(@PathVariable String email) {
		return repo.getUserByEmail(email);
	}

	@PostMapping("/userlogin")
	public ResponseEntity<?> userlogin(@RequestBody Entity obj) {
		var user = repo.getUserByEmail(obj.getEmail()).orElseThrow(() -> new RuntimeException("Usernotfound"));
		if (user.getStatus().equals("pending")) {
			throw new RuntimeException("User has Blocked");
		} else if (user.getPassword1().equals(obj.getPassword1())) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			throw new RuntimeException("Invalid password");
		}
	}

	@Autowired
	private CommentsRepo crepo;

	@GetMapping("/getcomments/{id}")
	public List<Comments> getcomments(@PathVariable int id) {
		return crepo.findByPostId(id);
	}
	
	@DeleteMapping("/deletecomment/{id}")
	public String delcoment(@PathVariable int id) {
		crepo.deleteById(id);
		return "deleted";
	}

	@PostMapping("/postcomments")
	public String postcomments(@RequestBody Comments obj) {
		crepo.save(obj);
		return "commented";
	}

	@Autowired
	private Profilerepo prepo;

	@GetMapping("/getprofile/{username}")
	public Optional<Profile> profile(@PathVariable String username) {
		return prepo.findById(username);
	}

	@Autowired
	private AdminRepo arepo;

	@PostMapping("/postadmin")
	public String postadmin(@RequestBody Admin obj) {
		this.arepo.save(obj);
		return "saved";
	}

	@PutMapping("/updatepass")
	public String updatepass(@RequestBody Admin obj) {
		this.arepo.save(obj);
		return "Updated";
	}

	@PostMapping("/adminlogin")
	public ResponseEntity<?> adminlogin(@RequestBody Admin obj) {
		var user = arepo.findById(obj.getAdminname()).orElseThrow(() -> new RuntimeException("Usernotfound"));
		if (user.getPassword().equals(obj.getPassword())) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			throw new RuntimeException("Invalid password");
		}
	}

	@Autowired
	private FollowRepo frepo;

	@PostMapping("/follow")
	public String follow(@RequestBody Follow obj) {
	    var boo=frepo.existsByFollowbyAndFollowto(obj.getFollowby(),obj.getFollowto() );
		if (!boo) {
	    frepo.save(obj);
		return "Follow";}
		else {
			frepo.deleteByFollowbyAndFollowto(obj.getFollowby(), obj.getFollowto());
			return "Followed";
		}
	}

	@GetMapping("/following/{followby}/{followto}")
	public Boolean following(@PathVariable String followby, @PathVariable String followto) {
		return frepo.existsByFollowbyAndFollowto(followby, followto);
	}
	
	@GetMapping("/findfollowers/{followby}")
	public List<Follow> following(@PathVariable String followby){
		return frepo.findByFollowTo(followby); 
	}
}