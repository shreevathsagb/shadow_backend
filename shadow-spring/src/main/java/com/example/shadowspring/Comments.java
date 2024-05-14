package com.example.shadowspring;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="comments")
public class Comments {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private int postid;
		private String commente;
		private String username;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getPostid() {
			return postid;
		}
		public void setPostid(int postid) {
			this.postid = postid;
		}
		public String getCommente() {
			return commente;
		}
		public void setCommente(String commente) {
			this.commente = commente;
		} 
}	