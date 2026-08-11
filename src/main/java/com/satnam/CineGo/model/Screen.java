package com.satnam.CineGo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.satnam.CineGo.enums.ScreenType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "screen_id")
    private String id;

    @Column(name = "screen_name")
    private String name;


    @Column(name = "screen_type")
    @Enumerated(EnumType.STRING)
    private ScreenType screenType;

    @ManyToOne
    @JoinColumn(
            name = "theatre_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_screen_theatre")
    )
    @JsonBackReference
    private Theatre theatre;


    @OneToMany(mappedBy = "screen" ,fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Show> shows;


}
