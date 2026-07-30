package com.satnam.CineGo.model;


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
    private ScreenType screenType;


    @ManyToOne
    @JoinColumn(
            name = "theatre_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_screen_theatre")
    )
    private Theatre theatre;


    @OneToMany(mappedBy = "screen" ,fetch = FetchType.LAZY)
    List<Show> shows;


}
