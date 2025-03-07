package gogo.gogostage.domain.game.persistence

import gogo.gogostage.domain.stage.root.persistence.Stage
import gogo.gogostage.domain.team.root.persistence.Team
import jakarta.persistence.*

@Entity
@Table(name = "tbl_game")
class Game(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id", nullable = false)
    val stage: Stage,

    @Column(name = "category", nullable = false)
    val category: GameCategory,

    @Column(name = "ect_name", nullable = true)
    val etcName: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    val system: GameSystem,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first_place_team_id", nullable = true)
    val firstPlaceTeam: Team,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_place_team_id", nullable = true)
    val secondPlaceTeam: Team,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "third_place_team_id", nullable = true)
    val thirdPlaceTeam: Team,

    @Column(name = "team_count", nullable = false)
    val teamCount: Int,

    @Column(name = "is_end", nullable = false)
    val isEnd: Boolean
)

enum class GameCategory {
    SOCCER, BASKET_BALL, BASE_BALL, VOLLEY_BALL, BADMINTON, LOL, ETC
}

enum class GameSystem {
    TOURNAMENT, FULL_LEAGUE, SINGLE
}
