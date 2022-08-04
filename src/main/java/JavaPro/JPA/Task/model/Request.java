package JavaPro.JPA.Task.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "allRequest")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userName")
    private String userName;


    @Column(name = "commentary")
    private String commentary;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "handled")
    private boolean handled;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Operator> operators;
}
