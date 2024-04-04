package org.d3if3121.wordz.ui.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.d3if3121.wordz.R
import org.d3if3121.wordz.model.WordsEvents
import org.d3if3121.wordz.model.WordsState
import org.d3if3121.wordz.ui.theme.poppinsFontFamily

object TopAppBarFinal {
    val warnaUngu = Color(0xFF4200DB)
    val warnaUnguTua = Color(0xFF3300A8)
    val warnaPutih = Color(0xFFFFFFFF)
    val warnaHitam = Color(0xFF000000)

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopAppFinal(navigasi: NavController, state: WordsState, quizIcon: Boolean, appIcon: Boolean, backIcon: Boolean,content: @Composable () -> Unit){
        val context = LocalContext.current
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 20.dp,
                    ambientColor = warnaHitam,
                    spotColor = warnaHitam,
                    shape = RectangleShape,
                    clip = true

                )
            ,
            title = {
                content()
            }
        )
        Column()
        {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 20.dp,
                        ambientColor = warnaHitam,
                        spotColor = warnaHitam,
                        shape = RectangleShape,
                        clip = true

                    ),
                navigationIcon = {

                },

                title = {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (appIcon == true) {
                            Image(
                                painter = painterResource(id = R.drawable.wordzwhite),
                                contentDescription = "App logo",
                                modifier = Modifier
                                    .size(60.dp)
                                    .padding(10.dp)
                            )
                        }

                        if (backIcon == true) {
                            IconButton(
                                onClick = {
                                    navigasi.popBackStack()
                                }, modifier = Modifier.padding(0.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowLeft,
                                    tint = Color.White,
                                    contentDescription = "Back Icon",
                                    modifier = Modifier.size(80.dp)
                                )
                            }
                        }

                        content()

                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = warnaUngu,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    if (quizIcon == true) {
                        IconButton(
                            onClick = {
                                if (state.wordsList.size < 5) {
                                    Toast.makeText(
                                        context,
                                        R.string.minimal_5,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    navigasi.navigate("QuizScreen")
                                }
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Create,
                                tint = Color.White,
                                contentDescription = "Quiz Icon",
                            )
                        }
                    } else {

                    }
                },
            )


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = warnaUnguTua)
                    .size(6.dp)
                    .padding(20.dp, 20.dp)

            ){

            }
        }
    }
}