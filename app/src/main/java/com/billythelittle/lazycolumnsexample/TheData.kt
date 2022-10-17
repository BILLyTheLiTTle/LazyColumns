/*
 * LazyColumns: Jetpack Compose enhanced LazyColumns and usage examples
 *   Copyright (C) 2022  Vasilis Tsapalos
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *
 *   the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.billythelittle.lazycolumnsexample

import com.billythelittle.lazycolumns.Item
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

class CustomListItem(
    override val type: String = "",
    override val subType: String = "",
    val title: String = "",
    val description: String = "",
    val imageUrl: String = ""
): Item(type, subType)

fun getTheData(): ImmutableList<CustomListItem> {
    return persistentListOf(
        CustomListItem("Movie", "Action",
            "Shang-Chi and the Legend of the Ten Rings",
            "Shang-Chi, the master of unarmed weaponry-based Kung Fu, is forced to " +
                    "confront his past after being drawn into the Ten Rings organization.",
            "https://static.posters.cz/image/1300/%CE%91%CF%86%CE%AF%CF%83%CE%B5%CF%82/shang-chi-and-the-legend-of-the-ten-rings-flex-i114390.jpg"),
        CustomListItem("Movie", "Action",
            "The Matrix Resurrections",
            "The plot is currently unknown.",
            "https://media.oneman.gr/onm-images/matrix-3.jpg"),
        CustomListItem("Movie", "Action",
            "Free Guy",
            "A bank teller discovers that he's actually an NPC inside a brutal, open world video game.",
            "https://www.athinorama.gr/lmnts/events/cinema/10072050/Poster.jpg"),
        CustomListItem("Movie", "Action",
            "The Suicide Squad",
            "Supervillains Harley Quinn, Bloodsport, Peacemaker and a collection of nutty " +
                    "cons at Belle Reve prison join the super-secret, super-shady Task Force X as they " +
                    "are dropped off at the remote, enemy-infused island of Corto Maltese.",
            "https://sm.ign.com/t/ign_gr/movie/s/suicide-sq/suicide-squad-2_9h82.200.jpg"),
        CustomListItem("Movie", "Action",
            "Kate",
            "A female assassin has 24 hours to get vengeance on her murderer before she dies.",
            "https://image.tmdb.org/t/p/w185/uQWgSRXeYRWCvGIX9LDNBW6XBYD.jpg"),
        CustomListItem("Movie", "Horror",
            "Candyman",
            "A sequel to the horror film Candyman (1992) that returns to the now-gentrified " +
                    "Chicago neighborhood where the legend began.",
            "https://upreviews.net/images/artwork/upreviews_-KiisqOOM-GwNoLaCO_V.jpg"),
        CustomListItem("Movie", "Horror",
            "Don't Breathe 2",
            "The sequel is set in the years following the initial deadly home invasion, " +
                    "where Norman Nordstrom (Stephen Lang) lives in quiet solace until his past sins " +
                    "catch up to him.",
            "https://images-na.ssl-images-amazon.com/images/I/914Wg3bzCGL._RI_.jpg"),
        CustomListItem("Movie", "Horror",
            "Last Night in Soho",
            "An aspiring fashion designer is mysteriously able to enter the 1960s where " +
                    "she encounters a dazzling wannabe singer. But the glamour is not all it appears " +
                    "to be and the dreams of the past start to crack and splinter into something darker.",
            "https://deadline.com/wp-content/uploads/2021/06/last-night-in-soho-crop-excl-2.jpg"),
        CustomListItem("Movie", "Horror",
            "Malignant",
            "Madison is paralyzed by shocking visions of grisly murders, and her torment " +
                    "worsens as she discovers that these waking dreams are in fact terrifying realities.",
            "https://media.oneman.gr/onm-images/HMKQGC5Q2NGSBCA3TIGZARMZFU.jpg"),
        CustomListItem("Movie", "Animation",
            "The Witcher: Nightmare of the Wolf",
            "Escaping from poverty to become a witcher, Vesemir slays monsters for coin and " +
                    "glory, but when a new menace rises, he must face the demons of his past.",
            "https://upload.wikimedia.org/wikipedia/en/thumb/4/4d/The_Witcher_Nightmare_of_the_Wolf.jpg/220px-The_Witcher_Nightmare_of_the_Wolf.jpg"),
        CustomListItem("Movie", "Animation",
            "PAW Patrol: The Movie",
            "Ryder and the pups are called to Adventure City to stop Mayor Humdinger " +
                    "from turning the bustling metropolis into a state of chaos.",
            "https://dx35vtwkllhj9.cloudfront.net/paramountpictures/paw-patrol-the-movie/images/regions/us/share-tout2.png"),
        CustomListItem("Movie", "Musical",
            "Cinderella",
            "A modern movie musical with a bold take on the classic fairy tale. Our " +
                    "ambitious heroine has big dreams and with the help of her fab Godmother, " +
                    "she perseveres to make them come true.",
            "https://lumiere-a.akamaihd.net/v1/images/g_cinderella1950_03_17805_4c9a7fe6.jpeg"),
        CustomListItem("Book", "Horror",
            "My Heart Is a Chainsaw",
            "In her quickly gentrifying rural lake town Jade sees recent events only " +
                    "her encyclopedic knowledge of horror films could have prepared her for",
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1623264202l/55711617.jpg"),
        CustomListItem("Book", "Horror",
            "The Dead and the Dark",
            "Courtney Gould’s thrilling debut The Dead and the Dark is about the things " +
                    "that lurk in dark corners, the parts of you that can’t remain hidden, and about " +
                    "finding home in places―and people―you didn’t expect.",
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1599058814l/53141419.jpg"),
        CustomListItem("Book", "Horror",
            "Billy Summers",
            "Billy Summers is a man in a room with a gun. He’s a killer for hire and the " +
                    "best in the business.",
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1618151020l/56852407.jpg"),
        CustomListItem("Book", "Horror",
            "A Lesson in Vengeance",
            "Perched in the Catskill mountains, the centuries-old, ivy-covered campus was " +
                    "home until the tragic death of her girlfriend.",
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1605799295l/50999821.jpg"),
        CustomListItem("Book", "Horror",
            "Velvet Was the Night",
            "From the New York Times bestselling author of Mexican Gothic comes a " +
                    "“delicious, twisted treat for lovers of noir” about a daydreaming secretary, a " +
                    "lonesome enforcer, and the mystery of a missing woman they’re both desperate to find.",
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1617426360l/54746205.jpg"),
        CustomListItem("Book", "Comic",
            "Star Wars: War Of The Bounty Hunters - Boushh",
            "THE SECRET ORIGIN OF BOUSHH! A “WAR OF THE BOUNTY HUNTERS” TIE-IN!",
            "https://i.annihil.us/u/prod/marvel/i/mg/f/90/6142606a6160a/clean.jpg"),
        CustomListItem("Book", "Comic",
            "X-Men: The Trial of Magneto",
            "Heroes of the Marvel Universe came to Krakoa for a memorial.",
            "https://i.annihil.us/u/prod/marvel/i/mg/7/03/61426068ad880/clean.jpg"),
        CustomListItem("Book", "Comic",
            "Extreme Carnage: Agony",
            "As the odds (and symbiotes!) stack against our heroes, is there any way " +
                    "they can win against Carnage?",
            "https://i.annihil.us/u/prod/marvel/i/mg/3/80/614260a6d95c8/clean.jpg"),
        CustomListItem("Book", "Comic",
            "The Last Annihilation: Wakanda",
            "With the universe itself at stake, Black Panther enlists the might of the " +
                    "Intergalactic Empire of Wakanda to help stop the dreaded Dormammu!",
            "https://i.annihil.us/u/prod/marvel/i/mg/3/90/61426088ebb66/clean.jpg"),
        CustomListItem("Book", "Comic",
            "Black Widow",
            "FRIEND OR FOE?",
            "https://i.annihil.us/u/prod/marvel/i/mg/6/90/612e8e0826ce5/clean.jpg")
    )
}

class CustomListItem2(
    val surname: String = "",
    val name: String = "",
)

fun getTheIndexedData(): ImmutableList<CustomListItem2> {
    return persistentListOf(
        CustomListItem2("A_Surname", "Name"),
        CustomListItem2("A_Surname", "Name"),
        CustomListItem2("B_Surname", "Name"),
        CustomListItem2("B_Surname", "Name"),
        CustomListItem2("B_Surname", "Name"),
        CustomListItem2("B_Surname", "Name"),
        CustomListItem2("B_Surname", "Name"),
        CustomListItem2("B_Surname", "Name"),
        CustomListItem2("B_Surname", "Name"),
        CustomListItem2("B_Surname", "Name"),
        CustomListItem2("C_Surname", "Name"),
        CustomListItem2("C_Surname", "Name"),
        CustomListItem2("C_Surname", "Name"),
        CustomListItem2("D_Surname", "Name"),
        CustomListItem2("D_Surname", "Name"),
        CustomListItem2("D_Surname", "Name"),
        CustomListItem2("E_Surname", "Name"),
        CustomListItem2("E_Surname", "Name"),
        CustomListItem2("E_Surname", "Name"),
        CustomListItem2("F_Surname", "Name"),
        CustomListItem2("F_Surname", "Name"),
        CustomListItem2("F_Surname", "Name"),
        CustomListItem2("F_Surname", "Name"),
        CustomListItem2("G_Surname", "Name"),
        CustomListItem2("G_Surname", "Name"),
        CustomListItem2("G_Surname", "Name"),
        CustomListItem2("G_Surname", "Name"),
        CustomListItem2("G_Surname", "Name"),
        CustomListItem2("G_Surname", "Name"),
        CustomListItem2("G_Surname", "Name"),
        CustomListItem2("G_Surname", "Name"),
        CustomListItem2("H_Surname", "Name"),
        CustomListItem2("I_Surname", "Name"),
        CustomListItem2("I_Surname", "Name"),
        CustomListItem2("I_Surname", "Name"),
        CustomListItem2("J_Surname", "Name"),
        CustomListItem2("J_Surname", "Name"),
        CustomListItem2("J_Surname", "Name"),
        CustomListItem2("K_Surname", "Name"),
        CustomListItem2("K_Surname", "Name"),
        CustomListItem2("K_Surname", "Name"),
        CustomListItem2("K_Surname", "Name"),
        CustomListItem2("K_Surname", "Name"),
        CustomListItem2("K_Surname", "Name"),
        CustomListItem2("K_Surname", "Name"),
        CustomListItem2("K_Surname", "Name"),
        CustomListItem2("L_Surname", "Name"),
        CustomListItem2("L_Surname", "Name"),
        CustomListItem2("L_Surname", "Name"),
        CustomListItem2("Z_Surname", "Name"),
        CustomListItem2("Z_Surname", "Name"),
    )
}