package gogo.gogostage.domain.team.root.application.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class TeamApplyDto(
    @NotBlank
    val teamName: String,
    @NotNull
    val participants: List<TeamParticipantDto>,
)

data class TeamParticipantDto(
    @NotNull
    val studentId: Long,
    @NotBlank
    val positionX: String,
    @NotBlank
    val positionY: String,
)
