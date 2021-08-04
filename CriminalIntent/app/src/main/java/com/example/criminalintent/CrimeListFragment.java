package com.example.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class CrimeListFragment extends Fragment {
    private static final int REQUEST_CRIME = 1;

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private List<Crime> mCrimes;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        mCrimes = crimeLab.getCrimes();
        updateUI();
        return view;
    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(mCrimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CRIME) {
            // Handle result
            if (data == null) {
                return;
            }

            UUID crimeId = (UUID) data.getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
            for (int i = 0; i < mCrimes.size(); i++) {
                if (crimeId.equals(mCrimes.get(i).getId())) {
                    mAdapter.notifyItemChanged(i);
                }
            }
        }
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;

        private Crime mCrime;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent, int layoutId) {
            super(inflater.inflate(layoutId, parent, false));

            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.crime_solved);
        }

        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.CHINA);
            mDateTextView.setText(df.format(crime.getDate()));
            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View v) {
//            Toast.makeText(getActivity(),
//                    mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT)
//                    .show();
//            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
//            startActivityForResult(intent, REQUEST_CRIME);
            Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());
//            startActivity(intent);
            startActivityForResult(intent, REQUEST_CRIME);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
//            return new CrimeHolder(layoutInflater, parent, R.layout.list_item_crime);
            if (viewType == 1) {
                return new CrimeHolder(layoutInflater, parent, R.layout.list_item_crime_police);
            } else {
                return new CrimeHolder(layoutInflater, parent, R.layout.list_item_crime);
            }
        }

        @Override
        public void onBindViewHolder(CrimeListFragment.CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }

        /***
         * 返回itemview的类型
         * @param position
         * @return
         */
        @Override
        public int getItemViewType(int position) {
            return mCrimes.get(position).isRequiresPolice() ? 1 : 0;
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

}
