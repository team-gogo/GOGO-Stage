package gogo.gogostage.domain.community.root.application

import gogo.gogostage.domain.community.board.persistence.Board
import gogo.gogostage.domain.community.board.persistence.BoardRepository
import gogo.gogostage.domain.community.boardlike.persistence.BoardLike
import gogo.gogostage.domain.community.boardlike.persistence.BoardLikeRepository
import gogo.gogostage.domain.community.comment.persistence.Comment
import gogo.gogostage.domain.community.comment.persistence.CommentRepository
import gogo.gogostage.domain.community.root.application.dto.LikeBoardResDto
import gogo.gogostage.domain.community.root.application.dto.WriteBoardCommentReqDto
import gogo.gogostage.domain.community.root.application.dto.WriteBoardCommentResDto
import gogo.gogostage.global.error.StageException
import gogo.gogostage.global.internal.student.stub.StudentByIdStub
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CommunityProcessor(
    private val boardLikeRepository: BoardLikeRepository,
    private val commentRepository: CommentRepository,
    private val boardRepository: BoardRepository,
    private val communityMapper: CommunityMapper
) {

    fun likeBoard(studentId: Long, board: Board): LikeBoardResDto {
        // 동시성 문제
        val isExistBoardLike = boardLikeRepository.existsByStudentIdAndBoardId(studentId, board.id)

        if (isExistBoardLike) {
            val boardLike = boardLikeRepository.findByBoardIdAndStudentId(board.id, studentId)
                ?: throw StageException("BoardLike Not Found", HttpStatus.NOT_FOUND.value())

            boardLikeRepository.delete(boardLike)

            board.minusLikeCount()
            boardRepository.save(board)

            return LikeBoardResDto(
                isLiked = false
            )
        } else {
            val boardLike = BoardLike(
                board = board,
                studentId = studentId
            )

            boardLikeRepository.save(boardLike)

            board.plusLikeCount()
            boardRepository.save(board)

            return LikeBoardResDto(
                isLiked = true
            )
        }
    }

    fun saveBoardComment(
        student: StudentByIdStub,
        writeBoardCommentDto: WriteBoardCommentReqDto,
        board: Board
    ): WriteBoardCommentResDto {
        val comment = Comment(
            board = board,
            studentId = student.studentId,
            content = writeBoardCommentDto.content,
            isFiltered = false,
            createdAt = LocalDateTime.now(),
            likeCount = 0
        )

        commentRepository.save(comment)

        return communityMapper.mapWriteBoardCommentResDto(comment, writeBoardCommentDto, student)
    }
}