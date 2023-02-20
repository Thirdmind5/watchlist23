package com.openclassrooms.watchlist23;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Validated
public class WatchlistController {

    private List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();
    private static int index = 1;

    @GetMapping("/watchlistItemForm")
    public ModelAndView showWatchlistItemForm(@RequestParam(required = false) Integer id){

        String viewName = "watchlistItemForm";
        Map<String,Object> model = new HashMap<String,Object>();

        WatchlistItem item = getWatchListItemById(id);

        if (item == null){
            model.put("watchlistItem", new WatchlistItem());
        }else
            model.put("watchlistItem", item);

        return new ModelAndView(viewName, model);

    }

    private WatchlistItem getWatchListItemById(Integer id) {

        for (WatchlistItem watchlistItem : watchlistItems) {
            if (watchlistItem.getId().equals(id)){
                return watchlistItem;
               }
        }
                return null;
    }

    @PostMapping("/watchlistItemForm")
    public  ModelAndView submitWatchlistItemForm(@Valid WatchlistItem watchlistItem,
                                                BindingResult bindingResult ){

        if (bindingResult.hasErrors()){
            return new ModelAndView ("watchlistItemForm");
        }
        WatchlistItem updateItem = getWatchListItemById(watchlistItem.getId());

        if (updateItem == null){
            watchlistItem.setId(index++);
            watchlistItems.add(watchlistItem);
        } else {
            updateItem.setComment(watchlistItem.getComment());
            updateItem.setPriority(watchlistItem.getPriority());
            updateItem.setRating(watchlistItem.getRating());
            updateItem.setTitle(watchlistItem.getTitle());
        }

        RedirectView redirect = new RedirectView();
        redirect.setUrl("/watchlist");

        return new ModelAndView(redirect);

    }

    @GetMapping("/watchlist")
    public ModelAndView getWatchlist(){

        String viewName = "watchlist";

        Map<String,Object> model = new HashMap<String,Object>();

        model.put("watchlistItems",watchlistItems);
        model.put("numberOfMovies",watchlistItems.size());

        return new ModelAndView(viewName, model);
    }

}
