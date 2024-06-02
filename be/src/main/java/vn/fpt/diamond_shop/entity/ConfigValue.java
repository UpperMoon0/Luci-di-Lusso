/*
package vn.fpt.diamond_shop.entity;

@Entity
@Table(name = "diamond_config_value")
@Data
@NoArgsConstructor
public class ConfigValue {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(name = "key", unique = true)
    private String key;
    @Column(name = "value")
    private String value;
    @Column(name = "description")
    private String description;
    @Column(name = "create_at")
    private OffsetDateTime createAt;

    @Column(name = "update_at")
    private OffsetDateTime updateAt;

    public ConfigValue(String key, String value, String description) {
        this.key = key;
        this.value = value;
        this.description = description;
        this.createAt = OffsetDateTime.now();
    }
}
*/
