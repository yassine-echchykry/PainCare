package PainCare.Beans;

import java.sql.Timestamp;

public class Comment_Bean {

	
		private int id;
		private int user_id;
		private int post_id;
		private String contenu;
		private Timestamp date;
		
		
		public int getID() {
			return this.id;
		}
		
		public int getUserID() {
			return this.user_id;
		}
		
		public int getPostID() {
			return this.post_id;
		}
		
		public String getContenu() {
			return this.contenu;
		}
		
		public Timestamp getDate() {
			return this.date;
		}
		
		
		public void setID(int id) {
			this.id = id;
		}
		
		public void setUserID(int user_id) {
			this.user_id = user_id;
		}
		
		public void setPostID(int post_id) {
			this.post_id = post_id;
		}
		
		public void setContenu(String contenu) {
			this.contenu = contenu;
		}
		
		public void setDate(Timestamp date) {
			this.date = date;
		}
		
	}

