import java.util.Objects;

public abstract class Attraction implements Comparable<Attraction> {
    private String town;
    private String description;
    private String name;

    private int colorCode;
    private boolean availability;

    private String color;

    private int popularity;//1 to 5 stars

    public Attraction(  String town, String name, String description, int colorCode, int popularity) {
        this.town = town;
        this.name = name;
        this.description = description;
        this.colorCode=colorCode;
        availability=true;
        color="\0";
        this.popularity=popularity;

    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColorCode() {
        return colorCode;
    }

    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean disponibility) {
        this.availability = disponibility;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Attraction{" +
                "town='" + town + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", colorCode=" + colorCode +
                ", availability=" + availability +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attraction that = (Attraction) o;
        return Objects.equals(town, that.town) && Objects.equals(description, that.description) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(town, description, name);
    }

    @Override
    public int compareTo(Attraction o) {
        return this.name.compareTo(o.name);
    }


}
