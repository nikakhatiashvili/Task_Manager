//package com.example.taskmanager.data.manageTribe
//
//import com.example.taskmanager.R
//import com.example.taskmanager.common.ResourceManager
//import com.example.taskmanager.common.Result
//import com.example.taskmanager.domain.manageTribe.TribeDataStore
//import com.example.taskmanager.domain.manageTribe.TribeIdRepository
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.channels.awaitClose
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.callbackFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//class TribeIdImpl @Inject constructor(
//    private val tribeDataStore: TribeDataStore,
//    private val firebaseAuth: FirebaseAuth,
//) : TribeIdRepository {
//    override suspend fun saveTribeId(){
//        tribeDataStore.saveTribeId(firebaseAuth.currentUser!!.uid)
//    }
//}
