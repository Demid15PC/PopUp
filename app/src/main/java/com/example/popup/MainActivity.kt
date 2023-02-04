package com.example.popup

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

       override fun onCreate(savedInstanceState: Bundle?) {
              super.onCreate(savedInstanceState)
              window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED)
              setContent {
                     OverFlowMenu()
              }
       }
}

@Composable
fun OverFlowMenu() {
       val context = LocalContext.current
       val expanded = remember { mutableStateOf(false) }
       val menuItems = listOf("Cancel", "Delete", "View")
       val scaffoldState = rememberScaffoldState()
       Scaffold(
              scaffoldState = scaffoldState,
              topBar = {
                     TopAppBar(title = { Text("Overflow Menu") },
                            backgroundColor = Color.Blue,
                            contentColor = Color.White,
                            actions = {

                            }
                     )
              }
       ) {
              Card(
                     modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, RoundedCornerShape(30.dp))
                            .padding(horizontal = 20.dp, vertical = 40.dp)
                            .height(250.dp),
                     elevation = 10.dp
              ) {
                     Column(
                            Modifier.fillMaxWidth()
                     ) {
                            Image(
                                   painter = painterResource(id = R.drawable.react_native_scl_alert),
                                   contentDescription = "img",
                                   modifier = Modifier.fillMaxWidth(),
                                   contentScale = ContentScale.FillBounds
                            )
                            Row(
                                   modifier = Modifier.fillMaxWidth(),
                                   horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                   Column(modifier = Modifier.fillMaxWidth(0.85f)) {
                                          Text(
                                                 text = "Здесь название моего крутого видео",
                                                 fontSize = MaterialTheme.typography.h6.fontSize,
                                                 fontWeight = FontWeight.Bold,
                                                 maxLines = 1,
                                                 overflow = TextOverflow.Ellipsis
                                          )
                                          Text(
                                                 text = "Это описание и тут может быть абсолютно всё что угодно " +
                                                         "и ещё немного текста для проверки",
                                                 overflow = TextOverflow.Ellipsis,
                                                 maxLines = 1
                                          )
                                   }
                                   Column(
                                          modifier = Modifier.fillMaxSize(),
                                          verticalArrangement = Arrangement.Top,
                                          horizontalAlignment = Alignment.End
                                   ) {
                                          IconButton(
                                                 onClick = {
                                                        expanded.value = true
                                                 }
                                          ) {
                                                 Icon(
                                                        imageVector = Icons.Default.MoreVert,
                                                        contentDescription = "image",
                                                        tint = Color.Black,
                                                 )
                                          }
                                          DropdownMenu(
                                                 expanded = expanded.value,
                                                 offset = DpOffset((-80).dp, (-20).dp),
                                                 onDismissRequest = { expanded.value = false })
                                          {
                                                 menuItems.forEach {
                                                        DropdownMenuItem(onClick = {
                                                               Toast.makeText(
                                                                      context,
                                                                      "You clicked $it menu",
                                                                      Toast.LENGTH_SHORT
                                                               ).show()
                                                               expanded.value = false
                                                        }) {
                                                               Text(text = it)
                                                        }
                                                 }
                                          }
                                   }

                            }
                     }
              }
       }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
       OverFlowMenu()
}