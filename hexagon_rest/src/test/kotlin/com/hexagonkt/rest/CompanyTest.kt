package com.hexagonkt.rest

import com.hexagonkt.server.jetty.JettyServletAdapter
import com.hexagonkt.rest.Department.*
import org.testng.annotations.Test
import java.net.URL
import java.nio.ByteBuffer
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Test class CompanyTest :
    CrudTest<Company, String>(Company::class, Company::id, JettyServletAdapter()) {

    override val testObjects: List<Company> = listOf (
        createObject(),
        Company(
            id = "id1",
            foundation = LocalDate.of(2014, 1, 25),
            closeTime = LocalTime.of(11, 42),
            openTime = LocalTime.of(8, 30)..LocalTime.of(14, 36),
            web = URL("http://example.org"),
            people = setOf(
                Person(name = "John"),
                Person(name = "Mike")
            )
        )
    )

    fun setObjectKey(obj: Company, id: Int) = obj.copy (id = id.toString ())

    fun changeObject(obj: Company) = obj.copy (notes = obj.notes + "_modified")

    fun createObject() = Company(
        id = "id",
        foundation = LocalDate.of(2014, 1, 25),
        closeTime = LocalTime.of(11, 42),
        openTime = LocalTime.of(8, 30)..LocalTime.of(14, 51),
        web = URL("http://example.org"),
        clients = listOf(
            URL("http://c1.example.org"),
            URL("http://c2.example.org")
        ),
        logo = ByteBuffer.wrap(byteArrayOf(0, 1, 2)),
        notes = "notes",
        people = setOf(
            Person(name = "John"),
            Person(name = "Mike")
        ),
        departments = setOf(DESIGN, DEVELOPMENT),
        creationDate = LocalDateTime.of(2016, 1, 1, 0, 0, 0)
    )

    fun check() {
        crud_operations_behave_properly()
    }
}
