package gogo.gogostage.domain.stage.participant.root.application

import gogo.gogostage.domain.stage.participant.root.application.dto.PointDto

interface ParticipantService {
    fun queryPoint(stageId: Long, studentId: Long): PointDto
}
