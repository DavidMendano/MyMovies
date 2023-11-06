package com.dmendanyo.data.repositories

import com.dmendanyo.data.PermissionChecker
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class LocationRepositoryTest {

    @Test
    fun `When coarse permission is not granted, then return default region`() = runBlocking {
        val sut = LocationRepositoryImpl(
            mock(),
            mock { on { check(PermissionChecker.Permission.ACCESS_COARSE_LOCATION) } doReturn false }
        )

        val result = sut.getRegion()

        assert(result == LocationRepositoryImpl.defaultRegion)
    }

    @Test
    fun `When coarse permission is granted and region is null, then return default region`() =
        runBlocking {
            val sut = LocationRepositoryImpl(
                mock { onBlocking { getLastKnownRegion() } doReturn null },
                mock { on { check(PermissionChecker.Permission.ACCESS_COARSE_LOCATION) } doReturn true }
            )

            val result = sut.getRegion()

            assert(result == LocationRepositoryImpl.defaultRegion)
        }

    @Test
    fun `When coarse permission is granted and region is not null, then return region`() =
        runBlocking {
            val region = "ES"
            val sut = LocationRepositoryImpl(
                mock { onBlocking { getLastKnownRegion() } doReturn region },
                mock { on { check(PermissionChecker.Permission.ACCESS_COARSE_LOCATION) } doReturn true }
            )

            val result = sut.getRegion()

            assert(result == region)
        }
}
