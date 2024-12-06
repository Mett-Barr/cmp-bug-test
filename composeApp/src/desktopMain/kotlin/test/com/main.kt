package test.com

import androidx.compose.ui.window.*

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun main() = singleWindowApplication {
    TestPage()
}

@Composable
fun TestPage(modifier: Modifier = Modifier) {
    Box {
        val list = remember { mutableStateListOf<Int>() }
        Row {
            val state = rememberLazyGridState()
            LazyVerticalGrid(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                columns = GridCells.Fixed(2),
                state = state,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 10.dp)
            ) {
                items(list, key = { it }) {
                    Text(
                        it.toString(),
                        Modifier.animateItem().padding(12.dp).clip(RoundedCornerShape(10.dp))
                            .background(Color.LightGray).padding(50.dp)
                    )
                }
            }
            VerticalScrollbar(rememberScrollbarAdapter(state))
        }
        Column(Modifier.align(Alignment.BottomEnd).padding(20.dp)) {
            Button({ list.add(0, list.size) }) { Text("+") }
            Button({ list.removeFirst() }) { Text("-") }
        }
    }
}