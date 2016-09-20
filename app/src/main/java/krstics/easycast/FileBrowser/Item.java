package krstics.easycast.FileBrowser;

class Item implements Comparable<Item>{

    private String name;
    private String data;
    private String date;
    private String path;
    private String image;

    Item(String n, String d, String dt, String p, String img)
    {
        name = n;
        data = d;
        date = dt;
        path = p;
        image = img;
    }
    String getName()
    {
        return name;
    }
    String getData()
    {
        return data;
    }
    String getDate()
    {
        return date;
    }
    String getPath()
    {
        return path;
    }
    String getImage() {
        return image;
    }


    @Override
    public int compareTo(Item item) {
        if(this.name != null)
            return this.name.toLowerCase().compareTo(item.getName().toLowerCase());
        else
            throw new IllegalArgumentException();
    }
}
