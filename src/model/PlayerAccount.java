package model;

public class PlayerAccount {
	
	private String username;
	private String password;
	private double balance;
	
	private int biggestBet;
	private boolean onARun;
	private int currentWinStreak;
	private int longestWinStreak;
	private int lowestBalance;
	private int highestBalance;
	
	public PlayerAccount(String username, String password) {
		this.username = username;
		this.password = username;
		balance = 100.0;
		biggestBet = 0;
		onARun = false;
		longestWinStreak = 0;
		lowestBalance = 0;
		highestBalance = 0;
	}
	
	/**
	 * Gets the player's username
	 * @return String username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Gets the player's username
	 * @return String password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Gets the players balance
	 * @return int balance
	 */
	public double getBalance() {
		return balance;
	}
	
	/**
	 * Gets the players biggestBet
	 * @return in biggestBet
	 */
	public int getBiggestBet() {
		return biggestBet;
	}
	
	/**
	 * Gets the current win streak
	 * @return int current win streak
	 */
	public int getCurrentWinStreak() {
		return currentWinStreak;
	}
	
	/**
	 * Gets the longest win streak
	 * @return int current win streak
	 */
	public int getLongestWinStreak() {
		return longestWinStreak;
	}
	
	/**
	 * Gets the lowest Balance
	 * @return int lowestBalance
	 */
	public int getLowestBalance() {
		return lowestBalance;
	}
	
	/**
	 * Gets the highest balance
	 * @return int highestBalance
	 */
	public int getHighestBalance() {
		return highestBalance;
	}
	


	
	
	
	
	
	
	
	/**
	 * Sets the player's username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Sets the player's username
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Sets the players balance
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	/**
	 * Sets the players biggestBet
	 */
	public void setBiggestBet(int bet) {
		biggestBet = bet;
	}
	
	/**
	 * Sets the current win streak
	 */
	public void incrementCurrentWinStreak() {
		currentWinStreak += 1;
	}
	
	/**
	 * Sets the longest win streak
	 */
	public void setLongestWinStreak(int winStreak) {
		longestWinStreak = winStreak;
	}
	
	/**
	 * Sets the lowest Balance
	 */
	public void setLowestBalance(int balance) {
		lowestBalance = balance;
	}
	
	/**
	 * Sets the highest balance
	 */
	public void setHighestBalance(int balance) {
		highestBalance = balance;
	}
	
	
	
}
