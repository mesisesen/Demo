import java.util.*;
import java.io.*;

public class Demo implements Serializable {
	
	int id;
	String name;
	String surname;
	int tel;
	
	Demo(int id, String name, String surname, int tel) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.tel = tel;
		}
	public String toString() {
		return id + " " + name + " " + surname + " " + tel;
	}
}

class TelDemo {
	public static void main(String[] args) throws Exception {
		int choice = -1;
		Scanner scan = new Scanner(System.in);
		Scanner scan1 = new Scanner(System.in);

		//Text file extraction
		File file = new File("output.txt");
		ArrayList<Demo> al = new ArrayList<Demo>();
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		ListIterator li = null;
		
		if(file.isFile()) {
			ois = new ObjectInputStream(new FileInputStream(file));
			al = (ArrayList<Demo>)ois.readObject();
			ois.close();			
		}
		
		do {
			System.out.println("1. INSERT");
			System.out.println("2. DISPLAY");
			System.out.println("3. SEARCH");
			System.out.println("4. DELETE");
			System.out.println("5. UPDATE");
			System.out.println("0. EXIT");
			System.out.println("Please select: ");
			choice = scan.nextInt();
			
		switch(choice) {
		//INSERT
		case 1:
			System.out.println("Enter how many records you want to enter:");
			int n = scan.nextInt();
			for(int i=0; i<n; i++) {
				System.out.print("Enter ID No.: ");
				int id = scan.nextInt();
				
				System.out.print("Enter Firstname: ");
				String name = scan1.nextLine();
				
				System.out.print("Enter Surname: ");
				String surname = scan1.nextLine();
				
				System.out.print("Enter Telephone No.: ");
				int tel = scan.nextInt();
				
				al.add(new Demo(id, name, surname, tel));
		}
		oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(al);
		oos.close();
		break;
		
		//DISPLAY
		case 2:
			if(file.isFile()) {
				ois = new ObjectInputStream(new FileInputStream(file));
				al = (ArrayList<Demo>)ois.readObject();
				ois.close();
				
			System.out.println("----------------------");
			li = al.listIterator();
			while(li.hasNext())
				System.out.println(li.next());
			System.out.println("----------------------");
			}else {
				System.out.println("File not exist.");
			}
		break;
		
		//SEARCH
		case 3:
			if(file.isFile()) {
				ois = new ObjectInputStream(new FileInputStream(file));
				al = (ArrayList<Demo>)ois.readObject();
				ois.close();
				
				boolean found = false;
				System.out.println("Enter ID No. to search: ");
				int id = scan.nextInt();
				System.out.println("----------------------");
				li = al.listIterator();
				while(li.hasNext()) {
					Demo d = (Demo)li.next();
					if(d.id == id) {
						System.out.println(d);
						found = true;
					}
				}
				if(!found)
					System.out.println("Record not found.");
				System.out.println("----------------------");
			} else {
				System.out.println("File not exist");
			}
			break;
		
		//DELETE
		case 4:
			if(file.isFile()) {
				ois = new ObjectInputStream(new FileInputStream(file));
				al = (ArrayList<Demo>)ois.readObject();
				ois.close();
				
				boolean found = false;
				System.out.print("Enter ID No. to delete a record: ");
				int id = scan.nextInt();
				System.out.println("----------------------");
				li = al.listIterator();
				while(li.hasNext()) {
					Demo d = (Demo)li.next(); 
					if(d.id == id) {
						li.remove();
						found = true;
					}
				}
				if(found) {
					oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(al);
					oos.close();
					System.out.println("Record has been deleted.");
				}
				else {
					System.out.println("Record not found.");
				}
				System.out.println("----------------------");
			} else {
				System.out.println("File not exist");
			}
			break;
			
		//UPDATE
		case 5: 
			if(file.isFile()) {
				ois = new ObjectInputStream(new FileInputStream(file));
				al = (ArrayList<Demo>)ois.readObject();
				ois.close();
				
				boolean found = false;
				System.out.print("Enter ID No. to update a record: ");
				int id = scan.nextInt();
				System.out.println("----------------------");
				li = al.listIterator();
				while(li.hasNext()) {
					Demo d = (Demo)li.next();
					if(d.id == id) {
						System.out.print("Enter new name: ");
						String name = scan1.nextLine();
						
						System.out.print("Enter new surname: ");
						String surname = scan1.nextLine();
						
						System.out.print("Enter new telephone: ");
						int tel = scan.nextInt();
						li.set(new Demo(id, name, surname, tel));
						found = true;
					}
				}
				if(found) {
					oos = new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(al);
					oos.close();
					System.out.println("Record has been updated.");
				}
				else {
					System.out.println("Record not found.");
				}
				System.out.println("----------------------");
			} else {
				System.out.println("File not exist");
			}
			break;
			}
		}while (choice !=0);
		
	}
}
