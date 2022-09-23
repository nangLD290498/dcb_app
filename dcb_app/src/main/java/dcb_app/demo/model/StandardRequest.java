package dcb_app.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class StandardRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String jsonRequest;

    @Column
    private String jsonResponse;

    @Column
    private String operatorRequest;

    @Column
    private String operatorResponse;
}
