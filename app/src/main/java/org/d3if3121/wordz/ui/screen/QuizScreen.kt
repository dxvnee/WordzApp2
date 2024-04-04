package org.d3if3121.wordz.ui.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.d3if3121.wordz.R
import org.d3if3121.wordz.model.Words
import org.d3if3121.wordz.model.WordsEvents
import org.d3if3121.wordz.model.WordsState
import org.d3if3121.wordz.ui.component.TopAppBarFinal
import org.d3if3121.wordz.ui.theme.WordzTheme
import org.d3if3121.wordz.ui.theme.poppinsFontFamily
import java.util.Calendar


@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    Coba3()
}

@Composable
fun Coba3(){

    WordzTheme {
        val state = WordsState(
            wordsList = listOf(
                Words("Word 1", "Meaning 1", "Notes 1", Calendar.getInstance().toString()),
                Words("Word 2", "Meani", "Notes 2", Calendar.getInstance().toString()),
                Words("Word 3", "Meaningdfd 3", "Notes 3", Calendar.getInstance().toString()),
                Words("Word 4", "Meaningdfd 3", "Notes 3", Calendar.getInstance().toString()),
                Words("Benar", "Jawaban", "Notes 3", Calendar.getInstance().toString()),
            ),
            words = remember { mutableStateOf("") },
            meaning = remember { mutableStateOf("") },
            notes = remember { mutableStateOf("") }
        )
        val navController = rememberNavController()

        QuizScreen(navController = navController, state = state) { }
    }
}

@Composable
fun QuizScreen(
    navController: NavController,
    state: WordsState,
    onEvent: (WordsEvents) -> Unit
) {
    var triggerRefresh by remember { mutableIntStateOf(0) }
    val quizIcon = false
    val appIcon = false
    val backIcon = true

    Scaffold(
        topBar = {
            TopAppBarFinal.TopAppFinal(navController, state, quizIcon, appIcon, backIcon){
                TopAppContextQuiz()
            }
        },


    ) { paddingValues ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(170.dp))

            Spacer(modifier = Modifier
                .height(70.dp)
                .padding(paddingValues))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .padding(5.dp),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 7.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = warnaPutih)
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Column(){
                        QuizApp(navController = navController, state = state, onEvent = onEvent,
                            triggerRefresh = triggerRefresh)
                    }


                }
            }


        }
    }
}




fun tambahIndex(indexBenar: Int): Int{
    return indexBenar + 1
}

@Composable
fun TopAppContextQuiz (){
    Text(text = stringResource(R.string.quiz),
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.ExtraBold,
        color = warnaPutih,
        modifier = Modifier.padding(vertical = 17.dp, horizontal = 3.dp)
    )
}

@Composable
fun QuizApp(navController: NavController, state: WordsState, onEvent: (WordsEvents) -> Unit, triggerRefresh: Int) {

    var triggerRefresh by remember { mutableStateOf(0) }
    var jumlahSoal by remember { mutableStateOf(1) }
    val correctAnswerIndex = remember { mutableStateOf((0..3).random()) }
    val contextt = LocalContext.current
    var wrongAnswerIndices = (0 until state.wordsList.size).filter { it != correctAnswerIndex.value }.toMutableList()

    var indexBenar by remember {mutableStateOf(0)}

    var score by remember { mutableStateOf(0) }
    var salah by remember { mutableStateOf(0) }

    var antierror by remember { mutableStateOf(false) }

    if(state.wordsList.size >= jumlahSoal) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (antierror == true){
               indexBenar = 0
                var scoreSekarang = score
                var salahSekarang = salah

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = stringResource(R.string.congratulation),
                        color = warnaUngu,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Button(
                        onClick = {
                            shareData(
                                context = contextt,
                                message = contextt.getString(R.string.bagikan_template,
                                    scoreSekarang.toString(), salahSekarang.toString())
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(50.dp),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = warnaUngu,
                            contentColor = warnaPutih
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.share),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End

                ){
                    Text(
                        text = stringResource(R.string.correct) + score,
                        color = warnaUngu,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = stringResource(R.string.wrong) + salah,
                            color = warnaMerah,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                        )
                    }

                }
            } else {
                Text(
                    text = state.wordsList[indexBenar].words,
                    color = warnaUngu,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = stringResource(R.string.choose_answer),
                    color = warnaUngu,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                )
                LazyColumn() {
                    items(4) { index ->
                        val answerText = if (index == correctAnswerIndex.value) {
                            state.wordsList[indexBenar].meaning
                        } else {
                            var wrongIndex = wrongAnswerIndices.random()
                            wrongAnswerIndices.remove(wrongIndex)
                            while(wrongIndex == indexBenar){
                                wrongIndex = wrongAnswerIndices.random()
                            }
                            state.wordsList[wrongIndex].meaning
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        TombolJawaban(
                            text = answerText,
                            onClick = {
                                if (index == correctAnswerIndex.value) {
                                    indexBenar = tambahIndex(indexBenar)
                                    score++
                                    jumlahSoal++
                                    triggerRefresh++
                                } else {
                                    indexBenar = tambahIndex(indexBenar)
                                    salah++
                                    jumlahSoal++
                                    triggerRefresh++
                                }
                            }
                        )
                    }
            }
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End

                ){
                    Text(
                        text = stringResource(R.string.correct) + score,
                        color = warnaUngu,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = stringResource(R.string.wrong) + salah,
                            color = warnaMerah,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                        )
                    }

                }
            }
        }
    } else {
        jumlahSoal = 1
        antierror = true

    }
    LaunchedEffect(triggerRefresh) {

        println("Refresh QuizApp")
        println(jumlahSoal)
        println(indexBenar)
        correctAnswerIndex.value = (0..3).random()
        wrongAnswerIndices = (0 until state.wordsList.size).filter { it != correctAnswerIndex.value }.toMutableList()
        wrongAnswerIndices.shuffle()

    }



}
private fun shareData(context: Context, message: String){
    val shareIntent = Intent(Intent.ACTION_SEND).apply{
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null){
        context.startActivity(shareIntent)
    }
}


@Composable
fun TombolJawaban(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .size(50.dp),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = warnaUngu,
            contentColor = warnaPutih
        )
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White
        )
    }
}

