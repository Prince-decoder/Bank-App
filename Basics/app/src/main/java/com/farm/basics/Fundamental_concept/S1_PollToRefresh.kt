package com.farm.basics.Fundamental_concept

import android.graphics.pdf.models.ListItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefreshIndicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Sample_Pulltorefresh()
{
    val scope= rememberCoroutineScope()
    var isRefreshing by remember {
        mutableStateOf({false})
    }
    var items by remember {
        mutableStateOf(List(20){"item $it"})
    }
    fun refietms()
    {
        scope.launch {
            isRefreshing= {true}
            delay(2000)
            items=List(20){"items #${(0..100).random()}"}
        }
    }
    PulltoRefreshCustome(
        items,
        isRefreshing =isRefreshing,
        onReresh = {refietms()}
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PulltoRefreshCustome(
    item: List<String>,
    isRefreshing: Boolean,
    onReresh:()->Unit,
    modifier: Modifier= Modifier
)
{
    val state= rememberPullToRefreshState()

    PullToRefreshBox(isRefreshing=isRefreshing,
        onReresh,
        state = state,
        modifier = modifier.fillMaxSize(),
        indicator = {
            MyCustomIndicator(
                state=state,
                isRefreshing=isRefreshing,
                modifier= Modifier.height(100.dp)
            )
        }) {

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCustomIndicator(
    state: PullToRefreshState,
    isRefreshing: Boolean,
    modifier: Modifier
)
{
    Box(modifier= Modifier.pullToRefreshIndicator(
        state=state,
        isRefreshing=isRefreshing,
        containerColor = PullToRefreshDefaults.containerColor,
        threshold = PullToRefreshDefaults.PositionalThreshold
    ),
       contentAlignment = Alignment.Center )
    {
        Crossfade(targetState = isRefreshing,
            animationSpec =tween(durationMillis = 1000),
            modifier = Modifier.align ( Alignment.Center )) {
            refreshing->
            if(isRefreshing)
            {
                CircularProgressIndicator(Modifier.size(20.dp)
            }
            else{

            }
        }
    }
}