package com.example.shadowspring;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="comments")
public class Comments {
		@Id
		private int id;
		private int postid;
		private String commente;
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
