package platform.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Entity
public class Code {

    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String uuid;

    private String code;

    @JsonProperty("time")
    private Long timeRestriction;

    @JsonProperty("views")
    private Long viewsRestriction;

    @JsonIgnore
    private Long numOfViewed = 0L;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime updateAt;

    @JsonProperty("date")
    public String getFormattedUpdateAt() {
        return updateAt == null ? "" : DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(updateAt);
    }

    @JsonGetter("time")
    public Long getAvailableSeconds() {
        return timeRestriction == 0 ? 0 : timeRestriction - updateAt.until(LocalDateTime.now(), ChronoUnit.SECONDS);
    }

    @JsonGetter("views")
    public Long getAvailableNumOfView() {
        return viewsRestriction == 0 ? 0 : viewsRestriction - numOfViewed;
    }

    public void countUpNumOfViewed() {
        numOfViewed++;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getTimeRestriction() {
        return timeRestriction;
    }

    public void setTimeRestriction(Long timeRestriction) {
        this.timeRestriction = timeRestriction;
    }

    public Long getViewsRestriction() {
        return viewsRestriction;
    }

    public void setViewsRestriction(Long viewsRestriction) {
        this.viewsRestriction = viewsRestriction;
    }

    public Long getNumOfViewed() {
        return numOfViewed;
    }

    public void setNumOfViewed(Long numOfViewed) {
        this.numOfViewed = numOfViewed;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
