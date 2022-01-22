package com.htwy.oa.dao.discuss;

import com.htwy.oa.entity.discuss.VoteTitleUser;
import com.htwy.oa.entity.discuss.VoteTitles;
import com.htwy.oa.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteTitlesUserDao extends JpaRepository<VoteTitleUser, Long> {

    //在用户投票的标题表中查找所有的同一标题的集合
    List<VoteTitleUser> findByVoteTitles(VoteTitles voteTitles);

    //在用户投票的标题表中查找所有的同一投票的集合
    List<VoteTitleUser> findByVoteId(Long voteId);

    VoteTitleUser findByVoteTitlesAndUser(VoteTitles voteTitles, User user);


}
