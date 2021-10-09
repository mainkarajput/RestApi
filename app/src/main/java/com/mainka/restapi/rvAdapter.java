package com.mainka.restapi;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.mainka.restapi.Entity.Model;

import java.util.ArrayList;

import static com.mainka.restapi.MainActivity.delete;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.ViewHolder> {

        // creating a variable for array list and context.
        private ArrayList<Model> modelArrayList;
        private Context context;
        User_Database database;

        // creating a constructor for our variables.
        public rvAdapter(ArrayList<Model> modelArrayList, Context context) {
            this.modelArrayList = modelArrayList;
            this.context = context;
        }

        @NonNull
        @Override
        public rvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // below line is to inflate our layout.
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.api_data_rv, parent, false);
            return new ViewHolder(view);
        }



    @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            final Model modal =modelArrayList.get(position);
            holder.name.setText(modal.getName());
            holder.agency.setText(modal.getAgency());
            holder.wikipedia.setText(modal.getWikipedia());
            holder.status.setText(modal.getStatus());


        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelArrayList.remove(position);
                notifyDataSetChanged();
            }
        });

            Glide.with(context)
                    .load(modelArrayList.get(position).getImage())
                    .into(holder.image);
            //Picasso.get().load(modal.getImage()).into(holder.image);
        }

        @Override
        public int getItemCount() {
            // returning the size of array list.
            return modelArrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            // creating variables for our views.
            private TextView name, agency, wikipedia,status;
            ImageView image;
            Button btn_delete;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                // initializing our views with their ids.
                name = itemView.findViewById(R.id.name);
                agency = itemView.findViewById(R.id.agency);
                wikipedia = itemView.findViewById(R.id.wikipedia);
                image = itemView.findViewById(R.id.image);
                status=itemView.findViewById(R.id.status);
                btn_delete=itemView.findViewById(R.id.delete);
                // btn_delete=itemView.findViewById(R.id.delete);
            }

        }
}
