package org.d3if3121.wordz.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.d3if3121.wordz.R
import org.d3if3121.wordz.model.WordsEvents
import org.d3if3121.wordz.model.WordsState
import org.d3if3121.wordz.ui.component.TopAppBarFinal
import org.d3if3121.wordz.ui.theme.WordzTheme
import org.d3if3121.wordz.ui.theme.poppinsFontFamily
import java.text.SimpleDateFormat
import java.util.Calendar



@Preview(showBackground = true)
@Composable
fun AddWordsPreview() {
    Coba2()
}

@Composable
fun Coba2(){
    WordzTheme {
        val navController = rememberNavController()
        val state = remember { WordsState() }

        AddWordsScreen(navController = navController, state = state) { }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWordsScreen(
    navController: NavController,
    state: WordsState,
    onEvent: (WordsEvents) -> Unit
) {

    var words by rememberSaveable { mutableStateOf("") }
    var meaning by rememberSaveable { mutableStateOf("") }
    var notes by rememberSaveable { mutableStateOf("") }

    var wordsError by rememberSaveable { mutableStateOf(false) }
    var meaningError by rememberSaveable { mutableStateOf(false) }
    var notesError by rememberSaveable { mutableStateOf(false) }
    
    val quizIcon = true
    val appIcon = false
    val backIcon = true

    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBarFinal.TopAppFinal(navController, state, quizIcon, appIcon, backIcon){
                TopAppContextAddWords()
            }
        },


    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(70.dp))
            Text(
                text = stringResource(R.string.add_new_words),
                color = warnaUngu,
                fontSize = 23.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(horizontal = 5.dp)
            )

            Spacer(modifier = Modifier
                .height(70.dp)
                .padding(paddingValues))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(11.dp))
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
                        .padding(20.dp)
                ) {
                    Text(
                        text = stringResource(R.string.words),
                        color = warnaUngu,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp, 10.dp))
                            .padding(vertical = 10.dp),
                        value = words,
                        onValueChange = { newText ->
                            if (newText.length <= 14) {
                                words = newText
                            }
                        },
                        trailingIcon = {
                            IconPicker(isError = wordsError, imageVector = Icons.Default.AddCircle)
                                       },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = warnaHitam
                        ),
                        supportingText = {
                            ErrorHint(wordsError)
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.ex_words),
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = warnaAbu
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = warnaUngu,
                            focusedBorderColor = warnaUngu,
                            focusedLabelColor = warnaUngu,
                            unfocusedLabelColor = warnaAbu,
                        ),
                        shape = RoundedCornerShape(15.dp),
                    )

                    Text(
                        text = stringResource(R.string.meaning_2),
                        color = warnaUngu,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp, 10.dp))
                            .padding(vertical = 10.dp),
                        value = meaning,
                        onValueChange = { newText ->
                            if (newText.length <= 14) {
                                meaning = newText
                            }
                        },
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        ),
                        supportingText = {
                            ErrorHint(meaningError)
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.ex_meaning),
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = warnaAbu
                            )
                        },
                        trailingIcon = {
                            IconPicker(isError = wordsError, imageVector = Icons.Default.Info)
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),

                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = warnaUngu,
                            focusedBorderColor = warnaUngu,
                            focusedLabelColor = warnaUngu,
                            unfocusedLabelColor = warnaAbu,
                        ),
                        shape = RoundedCornerShape(15.dp),

                    )

                    Text(
                        text = stringResource(R.string.notes),
                        color = warnaUngu,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp, 10.dp))
                            .padding(vertical = 10.dp),
                        value = notes,
                        onValueChange = { newText ->
                            if (newText.length <= 30) {
                                notes = newText
                            }
                        },
                        textStyle = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        ),
                        placeholder = {
                            Text(
                                text = stringResource(R.string.ex_notes),
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                color = warnaAbu
                            )
                        },
                        supportingText = {
                            ErrorHint(notesError)
                        },
                        trailingIcon = {
                            IconPicker(isError = wordsError, imageVector = Icons.Default.DateRange)
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = warnaUngu,
                            focusedBorderColor = warnaUngu,
                            focusedLabelColor = warnaUngu,
                            unfocusedLabelColor = warnaAbu,
                        ),
                        shape = RoundedCornerShape(15.dp),
                    )

                    val isChecked = InputWaktu() ?: false

                    Spacer(modifier = Modifier.height(40.dp))
                    Button(
                        onClick = {
                            val calendar = Calendar.getInstance()
                            val tanggalHariIni = calendar.time

                            val sdf = SimpleDateFormat("yyyy-MM-dd")

                            val tanggalDalamFormat = sdf.format(tanggalHariIni)

                            if(isChecked){
                                state.dateAdded.value = tanggalDalamFormat
                            } else {
                                state.dateAdded.value = ""
                            }

                            wordsError = (words == "")
                            meaningError = (meaning == "")
                            notesError = (notes == "")
                            if(wordsError || meaningError || notesError) return@Button

                            state.words.value = words
                            state.meaning.value = meaning
                            state.notes.value = notes

                            onEvent(WordsEvents.SaveWords(
                                words = state.words.value,
                                meaning = state.meaning.value,
                                notes = state.notes.value,
                                dateAdded = state.dateAdded.value
                            ))
                            navController.popBackStack()
                            Toast.makeText(context, R.string.data_add, Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(50.dp),
                        shape = RoundedCornerShape(15.dp),
                        content = {
                            Text(
                                text = stringResource(R.string.add_words),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = warnaPutih
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = warnaUngu,
                            contentColor = warnaPutih
                        )
                    )

                }
            }
        }
    }
}

@Composable
fun IconPicker(isError: Boolean, imageVector: ImageVector){
    if(isError){
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Icon(
            imageVector = imageVector,
            tint = Color.Gray,
            contentDescription = null,
            )
    }
}
@Composable
fun ErrorHint(isError: Boolean){
    if(isError){
        Text(text = stringResource(R.string.input_invalid))
    }
}

@Composable
fun InputWaktu(): Boolean? {

    val checkedState = remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {

        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
            colors = CheckboxDefaults.colors(checkedColor = warnaUngu, uncheckedColor = warnaAbu)
        )
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = if (checkedState.value) stringResource(R.string.save_date) else stringResource(R.string.not_save_date),
                color = warnaHitam,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,

            )
        }

    }
    return checkedState.value
}

@Composable
fun TopAppContextAddWords (){
    Text(text = stringResource(R.string.app_name),
        fontFamily = poppinsFontFamily,
        fontWeight = FontWeight.ExtraBold,
        color = warnaPutih,
        modifier = Modifier.padding(vertical = 17.dp, horizontal = 3.dp)
    )
}