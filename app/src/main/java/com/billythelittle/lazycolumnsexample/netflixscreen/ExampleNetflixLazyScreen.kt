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
package com.billythelittle.lazycolumnsexample.netflixscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.billythelittle.lazycolumns.MainContent
import com.billythelittle.lazycolumns.NetflixLazyScreen
import com.billythelittle.lazycolumns.PrimaryAppBar
import com.billythelittle.lazycolumns.SecondaryAppBar

@Composable
fun ExampleNetflixLazyScreen() {
    NetflixLazyScreen(
        primaryAppBar = {
            PrimaryAppBar(
                title = { Text("Primary App Bar") }
            )
        },
        secondaryAppBar = {
            SecondaryAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.secondary)
            ) {
                items(5) {
                    Button(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(horizontal = 5.dp),
                        onClick = { /*TODO*/ }) {
                        Text(
                            fontSize = 14.sp,
                            text = "Button ${it + 1}"
                        )
                    }
                }
            }
        }
    ) {
        MainContent {
            items(30) {

                Button(onClick = { /*TODO*/ }) {
                    Text(
                        "Vertical Button ${it+1}", modifier = Modifier
                            .wrapContentSize()
                            .padding(5.dp)
                    )
                }
            }
        }
    }
}