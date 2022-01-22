package com.htwy.oa.dao.discuss;

import com.htwy.oa.entity.discuss.VoteList;
import com.htwy.oa.entity.discuss.VoteTitles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteTitleListDao extends JpaRepository<VoteTitles, Long> {

    //根据投票id来找所有投票标题的集合
    List<VoteTitles> findByVoteList(VoteList voteList);
}
