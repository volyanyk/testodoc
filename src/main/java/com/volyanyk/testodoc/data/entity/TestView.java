package com.volyanyk.testodoc.data.entity;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "test_view", schema = "doc")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TestView {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "first_id")
    private Long firstId;

    @Column(name = "secondary_id")
    private Long secondaryId;

    @Column(name = "loading_city")
    private String loadingCity;

    @Column(name = "unloading_city")
    private String unloadingCity;

    @Column(name = "loading_country_code")
    private String loadingCountryCode;

    @Column(name = "unloading_country_code")
    private String unloadingCountryCode;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "source")
    private String source;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        TestView that = (TestView) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
