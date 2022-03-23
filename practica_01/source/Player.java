package practica_01;

import java.util.ArrayList;

public class Player implements Comparable<Player> {
	 private String playerName;
	    private ArrayList<String> teams;
	    private ArrayList<String> positions;
	    private int score;

	    public Player(String playerName, String team, String position, int score){
	        this.playerName = playerName;
	        this.teams = new ArrayList<String>();
	        this.teams.add(team);
	        this.positions = new ArrayList<String>();
	        this.positions.add(position);
	        this.score = score;
	    }

	    public String getPlayerName(){
	        return this.playerName;
	    }
	    public void setPlayerName(String playerName){
	        this.playerName = playerName;
	    }

	    public ArrayList<String> getTeams(){
	        return this.teams;
	    }
	    public void setTeams(ArrayList<String> teams){
	        this.teams = teams;
	    }

	    public ArrayList<String> getPositions(){
	        return this.positions;
	    }
	    public void setPositions(ArrayList<String> positions){
	        this.positions = positions;
	    }

	    public int getScore(){
	        return this.score;
	    }
	    public void setScore(int score){
	        this.score = score;
	    }
	    
	    public void addTeam(String team) {
	    	if(!this.teams.contains(team))
	    		this.teams.add(team);
	    }
	    
	    public void addPosition(String position) {
	    	if(!this.positions.contains(position))
	    		this.positions.add(position);
	    }

		@Override
		public String toString() {
			return "Player [playerName=" + playerName + ", teams=" + teams + ", positions=" + positions + ", score="
					+ score + "]";
		}

		public int compareTo(Player otro) {
	        int comp = -Integer.compare(this.getScore(), otro.getScore());
	        return comp;
	    }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
			result = prime * result + ((positions == null) ? 0 : positions.hashCode());
			result = prime * result + score;
			result = prime * result + ((teams == null) ? 0 : teams.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Player other = (Player) obj;
			if (playerName == null) {
				if (other.playerName != null)
					return false;
			} else if (!playerName.equals(other.playerName))
				return false;
			if (positions == null) {
				if (other.positions != null)
					return false;
			} else if (!positions.equals(other.positions))
				return false;
			if (score != other.score)
				return false;
			if (teams == null) {
				if (other.teams != null)
					return false;
			} else if (!teams.equals(other.teams))
				return false;
			return true;
		}  
}
