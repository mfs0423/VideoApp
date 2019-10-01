package entity;

import java.util.Objects;
import javafx.scene.image.Image;

public class Posters extends Image{
    
    private String videoId;
    private String url;

    public Posters(String url) {
        super(url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.videoId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Posters other = (Posters) obj;
        if (!Objects.equals(this.videoId, other.videoId)) {
            return false;
        }
        return true;
    }
    
    
}
