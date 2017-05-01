package br.brothers.mourao.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Document(collection = "model")
public class Model implements Serializable {

    private static final long serialVersionUID = -1035569096470168827L;

    @Id
    @JsonIgnore
    private String id;

    private String name;

    @JsonIgnore
    @Indexed(unique = true)
    private String collectionName;

    private Map<String, String> attributes;

    private List<String> restResources;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setCollectionName(name);
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName != null ? collectionName.toLowerCase() : null;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public List<String> getRestResources() {
        return restResources;
    }

    public void setRestResources(List<String> restResources) {
        this.restResources = restResources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;

        return collectionName != null ? collectionName.equals(model.collectionName) : model.collectionName == null;
    }

    @Override
    public int hashCode() {
        return collectionName != null ? collectionName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", collectionName='" + collectionName + '\'' +
                ", attributes=" + attributes +
                ", restResources=" + restResources +
                '}';
    }

}
