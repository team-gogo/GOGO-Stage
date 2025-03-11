package gogo.gogostage.global.kafka.properties

object KafkaTopics {
    const val MATCH_BETTING = "match_betting"
    const val MATCH_BETTING_FAILED = "match_betting_failed"
    const val MATCH_BATCH = "match_batch"
    const val BATCH_ADDITION_TEMP_POINT_FAILED = "batch_addition_temp_point_failed"
    const val BATCH_CANCEL = "batch_cancel"
    const val BATCH_CANCEL_DELETE_TEMP_POINT_FAILED = "batch_cancel_delete_temp_point_failed"
    const val TICKET_SHOP_BUY = "ticket_shop_buy"
    const val TICKET_POINT_MINUS = "ticket_point_minus"
    const val TICKET_POINT_MINUS_FAILED = "ticket_point_minus_failed"
    const val TICKET_ADDITION_FAILED = "ticket_addition_failed"
    const val STAGE_CREATE_OFFICIAL = "stage_create_official"
    const val STAGE_CREATE_FAST = "stage_create_fast"
}
