package org.inner.circle.o2oserver.member.infrastructure.repository

import org.inner.circle.o2oserver.member.domain.Member
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : MongoRepository<Member, String> {
    fun findBySnsTypeAndSubId(snsType: String, subId: String): Member?
}
