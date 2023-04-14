package com.example.taskmanager.presentation.detail

//class MessagesAdapter : RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {
//
//    var data: List<MessagesItem> = emptyList()
//        @SuppressLint("NotifyDataSetChanged")
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
//
//    inner class ViewHolder(
//        private val binding: MessageItemBinding,
//    ) : RecyclerView.ViewHolder(binding.root) {
//        private lateinit var currentData: MessagesItem
//
//        @RequiresApi(Build.VERSION_CODES.N)
//        fun bind() {
//            currentData = data[adapterPosition]
//
//            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US)
//            val outputFormat = SimpleDateFormat("M/d/yyyy hh:mm a", Locale.US)
//
//            val date = inputFormat.parse(currentData.date)
//            val outputStr = outputFormat.format(date)
//            with(binding) {
//                nameTextview.text = currentData.username
//                dateTextview.text = outputStr
//                contentTextview.text = currentData.message
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
//        ViewHolder(
//            MessageItemBinding.inflate(
//                LayoutInflater.from(parent.context),
//                parent,
//                false
//            )
//        )
//
//    @RequiresApi(Build.VERSION_CODES.N)
//    override fun onBindViewHolder(holder: MessagesAdapter.ViewHolder, position: Int) = holder.bind()
//
//    override fun getItemCount() = data.size
//
//}
