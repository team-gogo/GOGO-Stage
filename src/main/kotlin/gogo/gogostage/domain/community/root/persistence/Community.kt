package gogo.gogostage.domain.community.root.persistence

import gogo.gogostage.domain.game.persistence.GameCategory
import gogo.gogostage.domain.stage.root.persistence.Stage
import jakarta.persistence.*

@Entity
@Table(name = "tbl_community")
class Community(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long = 0,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id", nullable = false)
    val stage: Stage,

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    val category: GameCategory
) {
    companion object {

        fun of(stage: Stage, category: GameCategory) = Community(
            stage = stage,
            category = category
        )

    }
}

enum class SortType {
    LATEST, LAST
}
