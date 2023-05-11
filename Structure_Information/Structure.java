class Structure
{
    String description;
    Double timeComplex;
    int spaceComplex;
    String timeComplexName;
    String spaceComplexName;
    String name;
    //Space complexities are expressed on a scale 1-5, 5 being extremely expensive
    public Structure(String name, Double time, int space, String description, String timeS, String spaceS)
    {
        this.description = description;
        this.name = name;
        this.timeComplex = time;
        this.timeComplexName = timeS;
        this.spaceComplexName = spaceS;
        this.spaceComplex = space;
    }
    
    public Structure()
    {
        this.description = null;
        this.name = null;
        this.spaceComplexName = null;
        this.timeComplexName = null;
        this.spaceComplex = 0;
        this.timeComplex = 0.0;
    }
    
    public String getName()
    {
        return name;
    }
    public Double getTime()
    {
        return timeComplex;
    }
    public int getSpace()
    {
        return spaceComplex;
    }
    public String getDescription()
    {
        return description;
    }
    
    public String getSpaceS()
    {
        return spaceComplexName;
    }
    public String getTimeS()
    {
        return timeComplexName;
    }
    
    public String toString()
    {
        return getName() +  " is  " +  getSpace()  + " expensive and a timeComplexity of " + getTimeS() + ". " + getDescription();
    }
}