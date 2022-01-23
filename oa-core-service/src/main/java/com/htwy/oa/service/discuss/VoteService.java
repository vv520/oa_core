package com.htwy.oa.service.discuss;

import com.htwy.oa.entity.discuss.Discuss;
import com.htwy.oa.entity.discuss.VoteList;
import com.htwy.oa.entity.discuss.VoteTitleUser;
import com.htwy.oa.entity.user.User;
import org.springframework.ui.Model;

public interface VoteService {

    //保存一个投票
    VoteList savaVoteList(VoteList voteList);

    //保存投票标题与用户关联表
    VoteTitleUser savaVoteTitleUser(VoteTitleUser voteTitleUser);

    void voteServiceHandle(Model model, User user, Discuss discuss);
}
