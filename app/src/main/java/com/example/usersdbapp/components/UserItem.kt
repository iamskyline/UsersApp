package com.example.usersdbapp.components

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.usersdbapp.cardBorderColor
import com.example.usersdbapp.cardColor
import com.example.usersdbapp.data.UsersEntity

@Composable
fun UserItem(
    item: UsersEntity,
    onClickEdit: (UsersEntity) -> Unit,
    onClickDelete: (UsersEntity) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(1.dp, cardBorderColor, shape = RoundedCornerShape(15.dp)),
        colors = CardDefaults.cardColors(
            containerColor = cardColor,
        )
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .clickable {
                    Log.d("UserItem", "Clicked on UserItem")
                    onClickEdit(item)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row() {
                Text(text = item.Username, modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 0.dp))
                Text(text = item.Usersurname, modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 0.dp))
                Text(text = item.Userlastname, modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp))
            }
            IconButton(modifier = Modifier.size(30.dp),
                onClick = {
                    Log.d("UserItem", "Clicked on Delete button")
                    onClickDelete(item)
                }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}