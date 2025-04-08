class Player {
    private String status; //"P" for pause, empty if player is playing
    private int number; // random number or 0 for paused player
    private String name;

    public Player(){
        this.status = "";
        this.number = 0;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }
    public int getNumber(){
        return number;
    }
    public void setNumber(int number){
        this.number = number;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    
}
