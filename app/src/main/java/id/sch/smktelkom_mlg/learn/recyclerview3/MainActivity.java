import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.learn.recyclerview3.adapter.HotelAdapter;
import id.sch.smktelkom_mlg.learn.recyclerview3.model.Hotel;

public class MainActivity extends AppCompatActivity {
    public class MainActivity extends AppCompatActivity implements HotelAdapter.IHotelAdapter {
        public static final String HOTEL = "hotel";
        ArrayList<Hotel> mList = new ArrayList<>();
        HotelAdapter mAdapter;

        public void onClick(View view) {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            mAdapter = new HotelAdapter(mList);
            mAdapter = new HotelAdapter(this, mList);
            recyclerView.setAdapter(mAdapter);

            filData();

        private void filData() {
            Resources resources = getResources();
            String[] arJudul = resources.getStringArray(R.array.places);
            String[] arDeskripsi = resources.getStringArray(R.array.place_desc);
            String[] arDetail = resources.getStringArray(R.array.place_details);
            String[] arLokasi = resources.getStringArray(R.array.place_locations);
            TypedArray a = resources.obtainTypedArray(R.array.places_picture);
            Drawable[] arFoto = new Drawable[a.length()];
            String[] arFoto = new String[a.length()];
            for (int i = 0; i < arFoto.length; i++) {
                arFoto[i] = a.getDrawable(i);
                int id = a.getResourceId(i, 0);
                arFoto[i] = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                        + resources.getResourcePackageName(id) + '/'
                        + resources.getResourceTypeName(id) + '/'
                        + resources.getResourceEntryName(id);
            }
            a.recycle();

            for (int i = 0; i < arJudul.length; i++) {
                mList.add(new Hotel(arJudul[i], arDeskripsi[i], arFoto[i]));
                mList.add(new Hotel(arJudul[i], arDeskripsi[i], arDetail[i],
                        arLokasi[i], arFoto[i]));
            }
            mAdapter.notifyDataSetChanged();
        }

        public boolean onOptionsItemSelected(MenuItem item) {

            return super.onOptionsItemSelected(item);
        }

        @Override
        public void doClick(int pos) {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(HOTEL, mList.get(pos));
            startActivity(intent);
        }
        }

    View
    app/src/main/java/id/sch/smktelkom_mlg/learn/recyclerview3/adapter/HotelAdapter.java

    package id.sch.smktelkom_mlg.learn.recyclerview3.adapter

    import android.content.Context
    import android.net.Uri
    import android.support.v7.widget.RecyclerView
    import android.view.LayoutInflater
    import android.view.View

    import id.sch.smktelkom_mlg.learn.recyclerview3.R
    import id.sch.smktelkom_mlg.learn.recyclerview3.model.Hotel


    public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {
        ArrayList<Hotel> hotelList;

        public HotelAdapter(ArrayList<Hotel> hotelList) {
            IHotelAdapter mIHotelAdapter;

            public HotelAdapter(Context context, ArrayList < Hotel > hotelList) {
                this.hotelList = hotelList;
                mIHotelAdapter = (IHotelAdapter) context;
            }

            @Override
            public ViewHolder onCreateViewHolder (ViewGroup parent,int viewType){
                public HotelAdapter.ViewHolder onCreateViewHolder (ViewGroup parent,int viewType){
                    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
                    ViewHolder vh = new ViewHolder(v);
                    return vh;
                }

                @Override
                public void onBindViewHolder (ViewHolder holder,int position){
                    public void onBindViewHolder (HotelAdapter.ViewHolder holder,int position){
                        Hotel hotel = hotelList.get(position);
                        holder.tvJudul.setText(hotel.judul);
                        holder.tvDeskripsi.setText(hotel.deskripsi);
                        holder.ivFoto.setImageDrawable(hotel.foto);
                        holder.ivFoto.setImageURI(Uri.parse(hotel.foto));

                    }

                    @Override
                    public int getItemCount () {
                        return 0;
                    }

                    public interface IHotelAdapter {
                        void doClick(int pos);
                    }

                    public class ViewHolder extends RecyclerView.ViewHolder {
                        ImageView ivFoto;
                        TextView tvJudul;
                        TextView tvDeskripsi;

                        public ViewHolder(View itemView) {
                            super(itemView);
                            ivFoto = (ImageView) itemView.findViewById(R.id.imageView);
                            tvJudul = (TextView) itemView.findViewById(R.id.textViewJudul);
                            tvDeskripsi = (TextView) itemView.findViewById(R.id.textViewDeskripsi);

                            itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mIHotelAdapter.doClick(getAdapterPosition());
                                }
                            });
                        }
                    }
                }