import java.util.Objects;

public abstract class Attraction implements Comparable<Attraction> {
    private String town;
    private String description;

    private String name;

    public Attraction(  String town, String name, String description) {
        this.town = town;
        this.name = name;
        this.description = description;

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

    @Override
    public String toString() {
        return "Attraction{" +
                "town='" + town + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
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
