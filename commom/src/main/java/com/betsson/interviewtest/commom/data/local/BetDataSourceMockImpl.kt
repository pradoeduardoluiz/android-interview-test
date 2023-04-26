package com.betsson.interviewtest.commom.data.local

import com.betsson.interviewtest.commom.data.dto.BetDTO

class BetDataSourceMockImpl : BetDataSource {
    override suspend fun getBets(): List<BetDTO> {
        val bets = mutableListOf<BetDTO>()
        bets.add(
            BetDTO(
                type = "Winning team",
                sellIn = 10,
                odds = 20,
                image = "https://i.imgur.com/mx66SBD.jpeg"
            )
        )
        bets.add(
            BetDTO(
                type = "Total score",
                sellIn = 2,
                odds = 0,
                image = "https://i.imgur.com/VnPRqcv.jpeg"
            )
        )
        bets.add(
            BetDTO(
                type = "Player performance",
                sellIn = 5,
                odds = 7,
                image = "https://i.imgur.com/Urpc00H.jpeg"
            )
        )
        bets.add(
            BetDTO(
                type = "First goal scorer",
                sellIn = 0,
                odds = 80,
                image = "https://i.imgur.com/Wy94Tt7.jpeg"
            )
        )
        bets.add(
            BetDTO(
                type = "Number of fouls",
                sellIn = 5,
                odds = 49,
                image = "https://i.imgur.com/NMLpcKj.jpeg"
            )
        )
        bets.add(
            BetDTO(
                type = "Corner kicks",
                sellIn = 3,
                odds = 6,
                image = "https://i.imgur.com/TiJ8y5l.jpeg"
            )
        )
        return bets
    }
}
