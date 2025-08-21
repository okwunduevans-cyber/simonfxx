package com.example.simonfxx.model

/**
 * Vote summary and reasoning for approving/rejecting a signal.
 */
data class ApprovalDecision(
    val approvals: Int,          // number of "yes" votes
    val rejections: Int,         // number of "no" votes
    val quorum: Int,             // how many approvals needed to pass (e.g., 3 of 5)
    val reason: String? = null   // optional human-readable explanation
) {
    val passed: Boolean get() = approvals >= quorum
    val totalVotes: Int get() = approvals + rejections
}