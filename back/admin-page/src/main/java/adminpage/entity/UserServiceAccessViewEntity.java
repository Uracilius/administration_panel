package adminpage.entity;

import adminpage.util.converter.LongArrayConverter;
import adminpage.util.converter.StringArrayConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
@Table(name = "admin_users_view", schema = "esb")
public class UserServiceAccessViewEntity {


    @Id
    private Long userId;

    private String username;

    private String userDescription;

    private String userPassword;

    @Column(name = "service_ids", columnDefinition = "bigint[]")
    @Convert(converter = LongArrayConverter.class)
    private List<Long> serviceIds;

    @Column(name = "service_names", columnDefinition = "varchar[]")
    @Convert(converter = StringArrayConverter.class)
    private List<String> serviceNames;

    @Column(name = "service_descriptions", columnDefinition = "varchar[]")
    @Convert(converter = StringArrayConverter.class)
    private List<String> serviceDescriptions;


}
