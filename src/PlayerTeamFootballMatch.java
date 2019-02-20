import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ListIterator;

public class PlayerTeamFootballMatch {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// We create an empty ArrayList object of ArrayList type (that will be of
		// FootballMatch type)
		ArrayList<ArrayList<FootballMatch>> leagueArrayList = new ArrayList<ArrayList<FootballMatch>>();
		boolean argumentEntered=false;
		while (!argumentEntered) {
			// We control that the user enters an argument at least
			if (args.length != 0) {
				argumentEntered=true;
				ArrayList<Team> teams = new ArrayList<Team>();
				File teamsFile = new File("C:\\Users\\ik013043z1\\eclipse-workspace\\FootballMatch3\\src\\Teams.txt");
				ArrayList<FootballPlayer> footballPlayers = new ArrayList<FootballPlayer>();
				File playersFile = new File("C:\\Users\\ik013043z1\\eclipse-workspace\\FootballMatch3\\src\\Players.txt");
				boolean teamsFileFound = false;
				while (!teamsFileFound) {
					try {
						Scanner teamsScanner = new Scanner(teamsFile);
						while (teamsScanner.hasNext()) {
							String team = teamsScanner.nextLine();
							String[] teamInformation = team.split("::");
							Team thisTeam = new Team();
							thisTeam.setTeamName(teamInformation[0]);
							thisTeam.setCoach(teamInformation[1]);
							teams.add(thisTeam);
						}
						teamsScanner.close();
						teamsFileFound = true;
					} catch (FileNotFoundException e) {
						System.err.println("The file which contains the teams was not found, enter the correct name");
					} finally {
						if (!teamsFileFound) {
							String teamsPath = sc.nextLine();
							teamsFile = new File(
									"C:\\Users\\ik013043z1\\eclipse-workspace\\FootballMatch3\\src\\" + teamsPath + ".txt");
						}
					}
				}
				boolean playersFileFound = false;
				while (!playersFileFound) {
					try {
						Scanner playersScanner = new Scanner(playersFile);
						while (playersScanner.hasNext()) {
							String player = playersScanner.nextLine();
							String[] playerInformation = player.split("::");
							FootballPlayer thisPlayer = new FootballPlayer();
							thisPlayer.setPlayerName(playerInformation[0]);
							thisPlayer.setAge(Integer.parseInt(playerInformation[1]));
							thisPlayer.setTeamName(playerInformation[2]);
							footballPlayers.add(thisPlayer);
						}
						playersScanner.close();
						playersFileFound = true;
					} catch (FileNotFoundException e) {
						System.err.println("The file which contains the players was not found, enter the correct name");
					} finally {
						if (!playersFileFound) {
							String playersPath = sc.nextLine();
							playersFile = new File("C:\\Users\\ik013043z1\\eclipse-workspace\\FootballMatch3\\src\\"
									+ playersPath + ".txt");
						}
					}
				}
				// We create an empty array of the type File with the length of the number of
				// arguments the user entered
				File[] FileArray = new File[args.length];
				// We create an empty array of the type Scanner with the length of the number of
				// arguments
				Scanner[] ScannerArray = new Scanner[args.length];
				// We open a loop for each argument
				for (int i = 0; i < args.length; i++) {
					// We create a file object with a previously created text file
					FileArray[i] = new File(
							"C:\\Users\\ik013043z1\\eclipse-workspace\\FootballMatch\\src\\" + args[i] + "Matches.txt");
					boolean leagueFileFound = false;
					while (!leagueFileFound) {
						try {
							// We create a scanner object with the previously created file object
							ScannerArray[i] = new Scanner(FileArray[i]);
							// We create an empty ArrayList object of FootballMatch type
							ArrayList<FootballMatch> partidoFutbol = new ArrayList<FootballMatch>();
							// We create a loop where each line of the text file will be a match
							while (ScannerArray[i].hasNext()) {
								// We create a string object with the next line
								String match = ScannerArray[i].nextLine();
								// We create an array with the values of each part of the line, separated by
								// "::"
								String[] information = match.split("::");
								// We convert the string values that represent goals to integer
								int GoalsLocal = Integer.parseInt(information[2]);
								int GoalsVisitor = Integer.parseInt(information[3]);
								Team LocalTeam = new Team();
								LocalTeam.setTeamName(information[0]);
								Team VisitorTeam = new Team();
								VisitorTeam.setTeamName(information[1]);
								// We create an empty FootballMatch object
								FootballMatch thisFootballMatch = new FootballMatch();
								// We add the information of the match to the FootballMatch object with the
								// previously created methods
								thisFootballMatch.setLocalTeam(LocalTeam);
								thisFootballMatch.setVisitorTeam(VisitorTeam);
								thisFootballMatch.setGoalsLocal(GoalsLocal);
								thisFootballMatch.setGoalsVisitor(GoalsVisitor);
								// We add the FootballMatch object to the previously created ArrayList of
								// FootballMatch type
								partidoFutbol.add(thisFootballMatch);
							}
							// We add the ArrayList object to the previously created ArrayList of ArrayList
							// type;
							leagueArrayList.add(partidoFutbol);
							ScannerArray[i].close();
							leagueFileFound = true;
						} catch (FileNotFoundException e) {
							System.err.println(
									"The file of the league " + args[i] + " was not found, enter the correct name");
						} finally {
							if (!leagueFileFound) {
								String league = sc.nextLine();
								FileArray[i] = new File("C:\\Users\\ik013043z1\\eclipse-workspace\\FootballMatch\\src\\"
										+ league + "Matches.txt");
							}
						}
					}
				}
				// We create a loop for each ArrayList of the ArrayList
				for (int i = 0; i < leagueArrayList.size(); i++) {
					// We create a iterator with the ArrayList of FootballMatch type
					ListIterator<FootballMatch> it = leagueArrayList.get(i).listIterator();
					// We create a counter that will hold how many draws were in the matches
					int drawCounter = 0;
					// We create a loop where each turn the iterator will hold the values of each
					// match
					while (it.hasNext()) {
						// If there wasn't a draw in the match, we will print the information of the
						// match
						if (it.next().getGoalsLocal() != it.previous().getGoalsVisitor()) {
							Team localTeam = it.next().getLocalTeam();
							for (int j = 0; j < teams.size(); j++) {
								if (teams.get(j).getTeamName().equals(localTeam.getTeamName())) {
									System.out.println(teams.get(j).getTeamName() + "\n" + teams.get(j).getCoach());
									for (int k = 0; k < footballPlayers.size(); k++) {
										if (footballPlayers.get(k).getTeamName().equals(teams.get(j).getTeamName())) {
											System.out.println(footballPlayers.get(k).getPlayerName() + "  "
													+ footballPlayers.get(k).getAge());
										}
									}
									break;
								}
							}
							Team visitorTeam = it.previous().getVisitorTeam();
							for (int j = 0; j < teams.size(); j++) {
								if (teams.get(j).getTeamName().equals(visitorTeam.getTeamName())) {
									System.out.println(teams.get(j).getTeamName() + "\n" + teams.get(j).getCoach());
									for (int k = 0; k < footballPlayers.size(); k++) {
										if (footballPlayers.get(k).getTeamName().equals(teams.get(j).getTeamName())) {
											System.out.println(footballPlayers.get(k).getPlayerName() + "  "
													+ footballPlayers.get(k).getAge());
										}
									}
									break;
								}
							}
							System.out.println(it.next().getGoalsLocal() + "  " + it.previous().getGoalsVisitor());
							System.out.println();
							// We go to the next match
							it.next();
						}
						// Otherwise we will increment the draw counter and remove the match from the
						// ArrayList
						else {
							drawCounter++;
							it.remove();
						}
					}
					// We print how many matches have been printed and how many draws were
					System.out.println(leagueArrayList.get(i).size() + " matches have been displayed on the screen because "
							+ drawCounter + " draws were omitted");
					System.out.println();
				}
			} else {
				System.err.println("Please try again, you didn't enter any competition");
				args=sc.nextLine().split(" ");
			}
		}
		sc.close();
	}
}