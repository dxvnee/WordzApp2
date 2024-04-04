package org.d3if3121.wordz

import android.content.res.Configuration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import org.d3if3121.wordz.ui.theme.WordzTheme
import org.d3if3121.wordz.ui.screen.MenuScreen
import org.d3if3121.wordz.ui.screen.AddWordsScreen
import org.d3if3121.wordz.ui.theme.poppinsFontFamily
import org.d3if3121.wordz.model.Words
import org.d3if3121.wordz.model.WordsDao
import org.d3if3121.wordz.model.WordsDatabase
import org.d3if3121.wordz.model.WordsViewModel
import org.d3if3121.wordz.navigation.SetupNavGraph


class MainActivity : ComponentActivity() {
    //database
    private val db by lazy {
        Room.databaseBuilder(
            context = applicationContext,
            klass = WordsDatabase::class.java,
            name = "datamodel.db"
        ).build()
    }
    private val viewModell by viewModels<WordsViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return WordsViewModel(db.dao) as T
                }
            }
        }
    )

    //tampilan
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            WordzTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavGraph(viewModell)
                }
            }
        }
    }

    
}







@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Composable
//fun ListItem(catatan: Words, onClick: () -> Unit){
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable { onClick() }
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    )
//    {
//        Text(
//            text = catatan.word,
//            maxLines = 1,
//            overflow = TextOverflow.Ellipsis,
//            fontWeight = FontWeight.Bold
//        )
//        Text(
//            text = catatan.meaning,
//            maxLines = 1,
//            overflow = TextOverflow.Ellipsis
//        )
//        Text(
//            text = catatan.note,
//        )
//
//    }
//}
//@Composable
//fun ColumnPrint(vm: DataModelViewModel){
//    val dataList = vm.getAllRecords().collectAsState(initial = emptyList())
//
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(all = 16.dp)
//    ) {
//        items(dataList.value) { item ->
//            Row {
//                Text(item.word)
//                Spacer(Modifier.weight(1f))
//                Text(item.meaning.toString())
//                Spacer(Modifier.weight(1f))
//                Text(item.note.toString())
//            }
//        }
//    }
//}
//
//@Composable
//fun DatabaseAdd(vm: DataModelViewModel) {
//
//    var word by remember { mutableStateOf("") }
//    var meaning by remember { mutableStateOf("") }
//    var note by remember { mutableStateOf("") }
//
//    Column {
//        TextField(
//            value = word,
//            onValueChange = { word = it },
//            label = { Text("Word") }
//        )
//        TextField(
//            value = meaning,
//            onValueChange = { meaning = it },
//            label = { Text("Meaning") }
//        )
//        TextField(
//            value = note,
//            onValueChange = { note = it },
//            label = { Text("Note") }
//        )
//
//        Button(onClick = {
//            vm.addData(word, meaning, note)
//            word = ""
//            meaning = ""
//            note = ""
//        }) {
//            Text("Add Item")
//        }
//
//    }
//}
//
