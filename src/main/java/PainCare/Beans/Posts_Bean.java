package PainCare.Beans;

import java.sql.Date;
import java.sql.Timestamp;


public class Posts_Bean {
	
		private int id;
		private int user_id;
		private String title;
		private String description;
		private String image;
		private Date date;
		
		
		public int getID() {
			return this.id;
		}
		
		public int getUserID() {
			return this.user_id;
		}
		
		public String getTitle() {
			return this.title;
		}
		
		public String getDescription() {
			return this.description;
		}
		
		public String getImage() {
			return this.image;
		}
		
		public Date getDate() {
			return this.date;
		}
		
		
		public void setID(int id) {
			this.id = id;
		}
		
		public void setUserID(int user_id) {
			this.user_id = user_id;
		}
		
		public void setTitle(String title) {
			this.title = title;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
		
		public void setImage(String image) {
			this.image = image;
		}

		public void setDate(Date date) {
			this.date = date;
		}
		}
	

