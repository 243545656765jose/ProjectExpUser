
package Model;


public class candidates {
    private int id;
    private String name;
    private String last_name;
    private String second_name;
    private int id_number;
    private int age;
    private byte[] photo;
    private String party;
    private int votes;

    public candidates(int id, String name, String last_name, String second_name, int id_number, int age, byte[] photo, String party, int votes) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.second_name = second_name;
        this.id_number = id_number;
        this.age = age;
        this.photo = photo;
        this.party = party;
        this.votes = votes;
    }

    public candidates(String name, String last_name, String second_name, int id_number, int age, byte[] photo, String party) {
        this.name = name;
        this.last_name = last_name;
        this.second_name = second_name;
        this.id_number = id_number;
        this.age = age;
        this.photo = photo;
        this.party = party;
    }

    public candidates(int id, String name, String last_name, String second_name, int id_number, int age, byte[] photo, String party) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.second_name = second_name;
        this.id_number = id_number;
        this.age = age;
        this.photo = photo;
        this.party = party;
    }

   
  

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * @param last_name the last_name to set
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * @return the second_name
     */
    public String getSecond_name() {
        return second_name;
    }

    /**
     * @param second_name the second_name to set
     */
    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    /**
     * @return the id_number
     */
    public int getId_number() {
        return id_number;
    }

    /**
     * @param id_number the id_number to set
     */
    public void setId_number(int id_number) {
        this.id_number = id_number;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the photo
     */
    public byte[] getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    /**
     * @return the party
     */
    public String getParty() {
        return party;
    }

    /**
     * @param party the party to set
     */
    public void setParty(String party) {
        this.party = party;
    }

    /**
     * @return the votes
     */
    public int getVotes() {
        return votes;
    }

    /**
     * @param votes the votes to set
     */
    public void setVotes(int votes) {
        this.votes = votes;
    }
    
    
    
    
    
}
