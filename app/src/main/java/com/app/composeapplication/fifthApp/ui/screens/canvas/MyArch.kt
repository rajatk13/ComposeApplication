package com.app.composeapplication.fifthApp.ui.screens.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.lang.Math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ArchComposed(
    totalTime:Long,
    handlerColor:Color,
    inActiveBarColor: Color,
    activeBarColor:Color,
    modifier: Modifier= Modifier,
    initialValue:Float=0f,
    strokeWidth:Dp=5.dp
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var value by remember {
        mutableStateOf(initialValue)
    }
    var currentTime by remember {
        mutableStateOf(totalTime)
    }
    var isTimmerRunning by remember {
        mutableStateOf(false)
    }
    
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.onSizeChanged {
            size = it }
    ) {
        Canvas(modifier = modifier){
            drawArc(
                color = inActiveBarColor,
                startAngle = -215f,
                sweepAngle =    250f ,//* value,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap= StrokeCap.Round)
            )

            drawArc(
                color = activeBarColor,
                startAngle = -215f,
                sweepAngle =    250f  * value,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap= StrokeCap.Round)
            )

            val center = Offset(size.width/2f, size.height/2f)
            val beta  = (250* value+145f) *(PI/180f).toFloat()
            val r =  size.width/2f
            val a =  cos(beta) * r
            val b = sin(beta) * r

            drawPoints(
                listOf(Offset(center.x+a, center.y+b)),
                pointMode = PointMode.Points,
                strokeWidth = (strokeWidth*3f).toPx(),
                cap= StrokeCap.Round,
                color =handlerColor
            )

        }
        Text(
            text = (currentTime / 1000L).toString(),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Green
        )
        Button(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.Center), colors = ButtonDefaults.buttonColors(
            backgroundColor = if(isTimmerRunning || currentTime <=0L) Color.Green
                              else Color.Red,
            
        )) {
            Text(text = if (isTimmerRunning && currentTime >= 0L) "Stop"
                        else if (!isTimmerRunning && currentTime >= 0L)"Start"
                        else "Restart")
        }

    }
}