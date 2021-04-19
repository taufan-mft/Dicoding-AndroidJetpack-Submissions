package com.topanlabs.filmtopan.data

import com.topanlabs.filmtopan.R

/**
 * Created by taufan-mft on 4/19/2021.
 */
object TvFactory {
    private val names = listOf(
            "Arrow",
            "Doom Patrol",
            "Dragon Ball",
            "Fairy Tail",
            "Family Guy",
            "Flash",
            "Game of Thrones",
            "Gotham",
            "Grey's Anatomy",
            "Hanna"
    )

    private val years = listOf(
            "2012",
            "2019",
            "1986",
            "2009",
            "1999",
            "2014",
            "2011",
            "2014",
            "2005",
            "2019"
    )

    private val scores = listOf(
            66,
            76,
            81,
            78,
            70,
            77,
            84,
            75,
            82,
            75
    )
    private val descs = listOf(
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
            "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
            "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
            "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
            "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
            "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
            "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
            ""
    )

    private val language = listOf(
            "English",
            "English",
            "Japanese",
            "Japanese",
            "English",
            "English",
            "English",
            "English",
            "English",
            "English"
    )

    private val status = listOf(
            "Ended",
            "Returning Series",
            "Ended",
            "Ended",
            "Returning Series",
            "Returning Series",
            "Ended",
            "Ended",
            "Returning Series",
            "Returning Series"
    )

    private val ages = listOf(
            "TV-14",
            "TV-MA",
            "TV-PG",
            "TV-14",
            "TV-14",
            "TV-14",
            "TV-MA",
            "TV-14",
            "TV-14",
            "TV-MA"
    )

    private val tags = listOf(
            "Crime, Drama, Mystery, Action & Adventure",
            "Sci-Fi & Fantasy, Comedy, Drama",
            "Animation, Action & Adventure, Sci-Fi & Fantasy",
            "Action & Adventure, Animation, Comedy, Sci-Fi & Fantasy",
            "Animation, Comedy",
            "Drama, Sci-Fi & Fantasy",
            "Sci-Fi & Fantasy, Drama, Action & Adventure",
            "Drama",
            "Action & Adventure, Drama",
            "Drama"
    )

    private val runtimes = listOf(
            "42m",
            "49m",
            "25m",
            "25m",
            "22m",
            "44m",
            "1h",
            "43m",
            "50m",
            "43m"
    )

    private val pictures = listOf(
            R.drawable.poster_arrow,
            R.drawable.poster_doom_patrol,
            R.drawable.poster_dragon_ball,
            R.drawable.poster_fairytail,
            R.drawable.poster_family_guy,
            R.drawable.poster_flash,
            R.drawable.poster_god,
            R.drawable.poster_gotham,
            R.drawable.poster_grey_anatomy,
            R.drawable.poster_hanna,
    )

    val listData: List<TvModel>
        get() {
            val list = mutableListOf<TvModel>()
            for (pos in names.indices) {
                val film = TvModel(
                        name = names[pos],
                        year = years[pos],
                        userScore = scores[pos].toString(),
                        shortDesc = descs[pos],
                        language = language[pos],
                        status = status[pos],
                        ageRating = ages[pos],
                        tags = tags[pos],
                        runtime = runtimes[pos],
                        picture = pictures[pos]
                )
                list.add(film)
            }
            return list
        }
}